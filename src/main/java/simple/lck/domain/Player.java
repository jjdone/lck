package simple.lck.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import simple.lck.configuration.Position;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
public class Player {

    @Id @GeneratedValue
    @Column(name = "player_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Position position;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    private int pogPoint;
    private String detailUrl;

    @Builder
    public Player(String name, String nickname, Position position, Team team, int pogPoint, String detailUrl) {
        this.name = name;
        this.nickname = nickname;
        this.position = position;
        this.team = team;
        this.pogPoint = pogPoint;
        this.detailUrl = detailUrl;
    }
}
