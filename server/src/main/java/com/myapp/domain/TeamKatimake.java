package com.myapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by m on 2016/05/29.
 */
@Entity
@Table(name="teamtyokin")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamKatimake {
    @Id
    private String team;
    private Date update;
    private int tyokin;
}
