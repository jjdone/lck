package simple.lck.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Team {

    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
    private String headCoach;
    private int won;
    private int lost;
    private int score;
    private String detailUrl;
}
