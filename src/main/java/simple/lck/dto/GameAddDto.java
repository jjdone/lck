package simple.lck.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import simple.lck.configuration.Position;
import simple.lck.configuration.Season;
import simple.lck.domain.Player;
import simple.lck.domain.Team;

import java.time.LocalDateTime;

@Data
@Builder
public class GameAddDto {

    private Season season;
    private String round;
    private LocalDateTime startDate;

    private Team team1;
    private Player top1;
    private Player jgl1;
    private Player mid1;
    private Player bot1;
    private Player spt1;

    private Team team2;
    private Player top2;
    private Player jgl2;
    private Player mid2;
    private Player bot2;
    private Player spt2;

    private int team1_point;
    private int team2_point;
}
