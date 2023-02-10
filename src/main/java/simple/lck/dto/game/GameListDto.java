package simple.lck.dto.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import simple.lck.configuration.GameState;
import simple.lck.configuration.Season;
import simple.lck.domain.Team;

@Data
@AllArgsConstructor
@Builder
public class GameListDto {

    private Long id;
    private GameState gameState;
    private String round;
    private Season season;
    private Team team;
    private int point;
}
