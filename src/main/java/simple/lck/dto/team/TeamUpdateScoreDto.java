package simple.lck.dto.team;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamUpdateScoreDto {

    private int won;
    private int lost;
    private int score;
}
