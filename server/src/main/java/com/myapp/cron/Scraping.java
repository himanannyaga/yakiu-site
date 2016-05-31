package com.myapp.cron;

/**
 * 定期的にスクレイピングする
 */

import com.myapp.domain.TeamRank;
import com.myapp.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scraping {
    /** yahooのランキングurl */
    private static String RANK_URL = "http://baseball.yahoo.co.jp/npb/standings/";
    @Autowired
    TeamRepository repository;

    /** 10分毎に実行 */
    @Scheduled(initialDelay = 1000, fixedRate = 10 * 60 * 1000)
    public void FetchRank(){
        TeamRank team = new TeamRank();
        repository.save(team);
    }

}
