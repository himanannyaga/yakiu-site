package com.myapp.cron;

/**
 * 定期的にスクレイピングする
 */

import com.myapp.domain.TeamKatimake;
import com.myapp.repository.TeamRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class Scraping {
    /** yahooのランキングurl */
    private static String RANK_URL = "http://baseball.yahoo.co.jp/npb/standings/";
    @Autowired
    TeamRepository repository;

    @Scheduled(initialDelay = 1000, fixedRate = 1000000)
    public void FetchRank(){
        try {
            Document document = Jsoup.connect(RANK_URL).get();
            //セリーグ
            Elements elems = document.select("#sta_c table.NpbPlSt.yjM tr");
            //一行目抜かす
            elems.eq(0).remove();
            for (Element elem : elems) {
                System.out.println(elem.text());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        repository.save(new TeamKatimake("De",new Date(),0));
    }

}
