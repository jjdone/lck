package simple.lck.domain;

import lombok.Getter;
import simple.lck.configuration.GameState;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class Game {

    @Id
    @GeneratedValue
    @Column(name = "game_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    private Date date;

    @Enumerated(value = EnumType.STRING)
    private GameState gameState;
}
