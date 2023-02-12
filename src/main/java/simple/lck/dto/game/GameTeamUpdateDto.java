package simple.lck.dto.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import simple.lck.configuration.GameState;
import simple.lck.domain.Team;

@Data
@Builder
@AllArgsConstructor
public class GameTeamUpdateDto {

    private Team team1;
    private int point1;
    private Team team2;
    private int point2;
    private GameState gameState;
}
