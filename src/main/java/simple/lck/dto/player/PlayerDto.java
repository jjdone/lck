package simple.lck.dto.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import simple.lck.configuration.Position;
import simple.lck.domain.Player;
import simple.lck.domain.Team;

@Data
public class PlayerDto {

    private Long id;
    private String name;
    private String nickname;
    private Position position;
    private Long teamId;
    private int pogPoint;
    private String detailUrl;

    public PlayerDto(Player player) {
        id = player.getId();
        name = player.getName();
        nickname = player.getNickname();
        position = player.getPosition();
        teamId = player.getTeam().getId();
        pogPoint = player.getPogPoint();
        detailUrl = player.getDetailUrl();

    }
}
