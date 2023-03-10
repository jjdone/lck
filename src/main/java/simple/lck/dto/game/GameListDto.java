package simple.lck.dto.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import simple.lck.configuration.GameState;
import simple.lck.configuration.Season;
import simple.lck.domain.Team;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class GameListDto {

    private Long id;
    private GameState gameState;
    private String round;
    private Season season;
    private Team team;
    private int point;
    private LocalDateTime startDate;
}
