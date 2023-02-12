package simple.lck.dto.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import simple.lck.domain.Team;

@Data
@Builder
@AllArgsConstructor
public class GameTeamDto {

    private Team team;
    private int point;
}
