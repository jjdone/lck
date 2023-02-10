package simple.lck.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class GameTeam {

    @Id @GeneratedValue
    @Column(name = "game_team_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "game_id")
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

    @Builder
    public GameTeam(Team team, Player top, Player jgl, Player mid, Player bot, Player spt, int point) {
        this.team = team;
        this.top = top;
        this.jgl = jgl;
        this.mid = mid;
        this.bot = bot;
        this.spt = spt;
        this.point = point;
    }
}
