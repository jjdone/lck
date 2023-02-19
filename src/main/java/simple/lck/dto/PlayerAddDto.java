package simple.lck.dto;

import lombok.Data;
import simple.lck.configuration.Position;
import simple.lck.domain.Team;

@Data
public class PlayerAddDto {

    private String name;
    private String nickname;
    private Position position;
    private Long teamId;
    private int pogPoint;
    private String detailUrl;
}
