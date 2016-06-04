package com.myapp.repository;

import org.springframework.data.domain.Sort;
		import org.springframework.data.mongodb.repository.MongoRepository;
		import org.springframework.data.mongodb.repository.Query;

		import com.myapp.domain.TeamRank;

		import java.util.Date;

/**
 * Created by m on 2016/05/29.
 */
public interface TeamRepository extends MongoRepository<TeamRank, String> {
	@Query("{'type' : ?0}")
	TeamRank findLastInfo(String league, Sort sort);

	@Query("{type: ?0, updated?: ?1}")
	TeamRank findByLeaguAndUpdated(String league, Date updated);

}
