package simple.lck.dto.game;

import lombok.Builder;
import lombok.Data;
import simple.lck.domain.Team;


@Data
@Builder
public class GameTeamDto {

    private Team team;
    private int point;
}
