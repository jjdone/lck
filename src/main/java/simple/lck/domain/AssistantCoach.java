package simple.lck.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class AssistantCoach {

    @Id @GeneratedValue
    @Column(name = "coach_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @Column(name = "team_id")
    private Team team;

    private String name;
}
