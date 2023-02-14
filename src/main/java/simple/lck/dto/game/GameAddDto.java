package simple.lck.dto.game;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import simple.lck.configuration.Position;
import simple.lck.configuration.Season;
import simple.lck.domain.Player;
import simple.lck.domain.Team;

import java.time.LocalDateTime;

@Data
public class GameAddDto {

    private Season season;
    private String round;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startDate;

    private Long team1Id;
    private Long top1Id;
    private Long jgl1Id;
    private Long mid1Id;
    private Long bot1Id;
    private Long spt1Id;

    private Long team2Id;
    private Long top2Id;
    private Long jgl2Id;
    private Long mid2Id;
    private Long bot2Id;
    private Long spt2Id;

    private int team1_point;
    private int team2_point;
}
