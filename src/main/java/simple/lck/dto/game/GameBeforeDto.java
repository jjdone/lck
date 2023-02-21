package simple.lck.dto.game;

import lombok.Data;
import simple.lck.configuration.GameState;
import simple.lck.configuration.Season;

import java.time.LocalDateTime;

@Data
public class GameBeforeDto {

    private Long gameId;
    private Long team1Id;
    private Long team2Id;
    private int team1Point;
    private int team2Point;
    private GameState gameState;
    private Season season;
    private String round;
    private LocalDateTime startDate;

    public GameBeforeDto(GameListDto gameListDto1, GameListDto gameListDto2) {
        gameId = gameListDto1.getId();
        team1Id = gameListDto1.getTeam().getId();
        team2Id = gameListDto2.getTeam().getId();
        team1Point = gameListDto1.getPoint();
        team2Point = gameListDto2.getPoint();
        gameState = gameListDto1.getGameState();
        season = gameListDto1.getSeason();
        round = gameListDto1.getRound();
        startDate = gameListDto1.getStartDate();
    }
}
