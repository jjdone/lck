package simple.lck.dto;

import lombok.Data;

@Data
public class TeamUpdateScoreDto {

    private int won;
    private int lost;
    private int score;
}
