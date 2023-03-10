package simple.lck.dto.game;

import lombok.Builder;
import lombok.Data;
import simple.lck.configuration.GameState;
import simple.lck.configuration.Season;
import simple.lck.domain.Game;
import simple.lck.domain.Team;

import java.time.LocalDateTime;

@Data
@Builder
public class GameScheduleDto {

    private Game game;
    private Season season;
    private String round;
    private GameState gameState;
    private Team team1;
    private Team team2;
    private int team1_point;
    private int team2_point;
    private LocalDateTime startDate;
}
