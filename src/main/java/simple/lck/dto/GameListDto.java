package simple.lck.dto;

import lombok.Data;
import simple.lck.configuration.GameState;
import simple.lck.configuration.Season;
import simple.lck.domain.Team;

@Data
public class GameListDto {

    private Long game_id;
    private GameState gameState;
    private String round;
    private Season season;
    private Team team;
    private int point;
}
