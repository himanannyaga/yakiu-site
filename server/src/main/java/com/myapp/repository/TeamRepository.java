package com.myapp.repository;

import com.myapp.domain.TeamKatimake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by m on 2016/05/29.
 */
@Repository
public interface TeamRepository extends JpaRepository<TeamKatimake, String> {

}
