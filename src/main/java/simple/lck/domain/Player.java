package simple.lck.domain;

import lombok.Getter;
import simple.lck.configuration.Position;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
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
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    private String detailUrl;
}
