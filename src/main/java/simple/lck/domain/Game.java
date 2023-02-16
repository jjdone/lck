package simple.lck.domain;

import lombok.Getter;
import lombok.Setter;
import simple.lck.configuration.GameState;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;
import static simple.lck.configuration.GameState.*;

@Entity
@Getter @Setter
public class Game {

    @Id
    @GeneratedValue
    @Column(name = "game_id")
    private Long id;

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "date_id")
    private Date date;

    @Enumerated(value = EnumType.STRING)
    private GameState gameState;

    @OneToMany(mappedBy = "game", cascade = ALL)
    private List<GameTeam> gameTeams = new ArrayList<>();

    private void addGameTeam(GameTeam gameTeam) {
        gameTeams.add(gameTeam);
        gameTeam.setGame(this);
    }

    // 생성 메서드
    public static Game createGame(Date date, GameTeam gameTeam1, GameTeam gameTeam2) {
        Game game = new Game();
        game.setDate(date);
        game.addGameTeam(gameTeam1);
        game.addGameTeam(gameTeam2);

        LocalDateTime now = LocalDateTime.now();

        if (date.getStartDate().isAfter(now)) {
            game.setGameState(BEFORE_GAME);
            return game;
        }
        game.setGameState(END);
        return game;
    }

    public void updatePoint(Team team1, int point1, Team team2, int point2, GameState gameState) {
        this.gameState = gameState;

        if (gameTeams.get(0).getTeam().equals(team1)) {
            gameTeams.get(0).setPoint(point1);
            gameTeams.get(1).setPoint(point2);
            return;
        }
        gameTeams.get(0).setPoint(point2);
        gameTeams.get(1).setPoint(point1);
    }
}
