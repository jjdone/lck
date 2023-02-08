package simple.lck.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class GameTeam {

    @Id @GeneratedValue
    @Column(name = "game_team_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "geme_id")
    private Game game;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = LAZY)
    private Player top;

    @ManyToOne(fetch = LAZY)
    private Player jgl;

    @ManyToOne(fetch = LAZY)
    private Player mid;

    @ManyToOne(fetch = LAZY)
    private Player bot;

    @ManyToOne(fetch = LAZY)
    private Player spt;

    private int point;
}
