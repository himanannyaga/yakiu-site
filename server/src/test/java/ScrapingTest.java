import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myapp.App;
import com.myapp.domain.TeamRank;
import com.myapp.domain.Tyokin;
import com.myapp.repository.TeamRepository;

/**
 * テストコード？
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class ScrapingTest {
    @Autowired
    TeamRepository repository;

    @Autowired
    MongoTemplate mongo;

    /** yahooのランキングurl */
    private static final String RANK_URL = "http://baseball.yahoo.co.jp/npb/standings/";
    private static final String CE_ID = "#sta_c";
    private static final String PA_ID = "#sta_p";
    private Document doc;

    @Test
    public void mongoTest() {
        TeamRank team = new TeamRank();
        //ValidationUtil.valid(team);
        Query query = new Query(Criteria.where("type").is("ce"));
        TeamRank hoge = mongo.findOne(query, TeamRank.class);
        System.out.println(hoge);

    }

    @Test
    public void hoge() throws IOException {
        doc = Jsoup.connect(RANK_URL).get();
        //セリーグ
        TeamRank ceLeagu = new TeamRank();
        ceLeagu.setType("ce");
        ceLeagu.setTeams(fetchTyokin(CE_ID));
        ceLeagu.setUpdated(fetchUpdated(CE_ID));
        ceLeagu.setUpdated(new Date());
        System.out.println(ceLeagu);

        TeamRank paLeagu = new TeamRank();
        paLeagu.setType("pa");
        paLeagu.setTeams(fetchTyokin(PA_ID));
        paLeagu.setUpdated(fetchUpdated(PA_ID));
        System.out.println(paLeagu);
        TeamRank hoge = repository.findByLeaguAndUpdated(ceLeagu.getType(), ceLeagu.getUpdated());
        System.out.println(hoge);
        repository.save(ceLeagu);
        repository.save(paLeagu);

    }

    /** チームごとの貯金とる */
    public  List<Tyokin> fetchTyokin(String selector){
        List<Tyokin> list = new ArrayList<>();
        final Elements elms = doc.select(selector + " table.NpbPlSt.yjM tr");
        //一行目ヘッダなので抜かす
        elms.remove(0);
        for (Element elem : elms) {
            //球団コード
            final String teamCode = elem.select("td.lt.NpbLogo.yjM > a").attr("class");

            //勝数
            final int tyokin = Integer.parseInt(elem.select("td:nth-child(4)").text());
            final int syakkin =Integer.parseInt(elem.select("td:nth-child(5)").text());

            //チームオブジェクト作成
            final Tyokin team = new Tyokin();
            team.setName(teamCode);
            team.setTyokin(tyokin - syakkin);
            list.add(team);
        }
        return list;
    }

    /** 更新日時取る、正規表現や数値変換だけで冗長すぎ */
    Date fetchUpdated(String selector){
        final Element elm = doc.select(selector + " .left.yjMS").get(0);
        Pattern p = Pattern.compile("([0-9]{4})年([0-9]+)月([0-9]+)日\\s([0-9]+)時([0-9]+)分更新");
        Matcher m = p.matcher(elm.text());
        if(m.find()){
            return new GregorianCalendar(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)) - 1,Integer.parseInt(m.group(3)),
                    Integer.parseInt(m.group(4)),Integer.parseInt(m.group(5))).getTime();
        }
        throw new RuntimeException("おかしい");
    }
}
//enumのつくりかた
enum Team {
    G("巨人"),
    T("阪神"),
    C("広島"),
    DB("横浜"),
    D("中日"),
    S("東京"),
    H("便器"),
    M("千葉"),
    F("日公"),
    L("西武"),
    Bs("檻牛"),
    E("楽天");


    private String label;

    Team(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
