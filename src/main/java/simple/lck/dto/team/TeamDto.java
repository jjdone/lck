package simple.lck.dto.team;

import lombok.Data;
import simple.lck.domain.Team;

@Data
public class TeamDto {

    private Long id;
    private String name;
    private String headCoach;
    private int won;
    private int lost;
    private int score;
    private String detailUrl;

    public TeamDto(Team team) {
        id = team.getId();
        name = team.getName();
        headCoach = team.getHeadCoach();
        won = team.getWon();
        lost = team.getLost();
        score = team.getScore();
        detailUrl = team.getDetailUrl();
    }
}
