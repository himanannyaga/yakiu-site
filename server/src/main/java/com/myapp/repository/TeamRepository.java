package com.myapp.repository;

import com.myapp.domain.TeamRank;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by m on 2016/05/29.
 */
public interface TeamRepository extends MongoRepository<TeamRank, String> {

}
