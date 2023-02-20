package simple.lck.dto.player;

import lombok.Data;
import simple.lck.configuration.Position;

@Data
public class PlayerAddDto {

    private String name;
    private String nickname;
    private Position position;
    private Long teamId;
    private int pogPoint;
    private String detailUrl;
}
