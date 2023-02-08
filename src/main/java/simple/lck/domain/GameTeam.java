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
    @Column(name = "geme_id")
    private Game game;

    @ManyToOne(fetch = LAZY)
    @Column(name = "team_id")
    private Team team;

    @ManyToOne(fetch = LAZY)
    private Player topPlayer;

    @ManyToOne(fetch = LAZY)
    private Player jglPlayer;

    @ManyToOne(fetch = LAZY)
    private Player midPlayer;

    @ManyToOne(fetch = LAZY)
    private Player botPlayer;

    @ManyToOne(fetch = LAZY)
    private Player sptPlayer;

    private int point;
}
