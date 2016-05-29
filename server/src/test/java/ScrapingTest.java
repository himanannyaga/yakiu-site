import com.myapp.domain.TeamKatimake;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * テストコード？
 */
public class ScrapingTest {

    /** yahooのランキングurl */
    private static final String RANK_URL = "http://baseball.yahoo.co.jp/npb/standings/";
    private static final String CE_ID = "#sta_c";
    private static final String PA_ID = "#sta_p";
    private Date nowDate;
    private List<TeamKatimake> teamList;
    @Test
    public void hoge() throws IOException {
        nowDate = new Date();
        teamList = new ArrayList<>();
        final Document document = Jsoup.connect(RANK_URL).get();
        //セリーグ
        scraping(document, CE_ID);
        scraping(document, PA_ID);
    }

    public void scraping(Document document, String selector){
        //セリーグ
        final Elements elms = document.select(selector + " table.NpbPlSt.yjM tr");
        //一行目ヘッダなので抜かす
        elms.remove(0);
        for (Element elem : elms) {
            //球団コード
            final String teamCode = elem.select("td.lt.NpbLogo.yjM > a").attr("class");

            //勝数
            final int tyokin = Integer.parseInt(elem.select("td:nth-child(4)").text());
            final int syakkin =Integer.parseInt(elem.select("td:nth-child(5)").text());

            //チームオブジェクト作成
            final TeamKatimake team = new TeamKatimake();
            team.setTeam(teamCode);
            team.setTyokin(tyokin - syakkin);
            team.setUpdate(nowDate);
            teamList.add(team);
        }
    }
}

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
