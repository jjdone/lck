package simple.lck.dto.team;

import lombok.Data;
import simple.lck.domain.AssistantCoach;
import simple.lck.domain.Team;

import java.util.List;

import static java.util.stream.Collectors.*;

@Data
public class TeamDetailsDto {

    private Long id;
    private String name;
    private String headCoach;
    private int won;
    private int lost;
    private int score;
    private String detailUrl;
    private List<String> assistantCoachNames;

    public TeamDetailsDto(Team team, List<AssistantCoach> assistantCoaches) {
        id = team.getId();
        name = team.getName();
        headCoach = team.getHeadCoach();
        won = team.getWon();
        lost = team.getLost();
        score = team.getScore();
        detailUrl = team.getDetailUrl();
        assistantCoachNames = assistantCoaches.stream()
                .map(assistantCoach -> assistantCoach.getName())
                .collect(toList());
    }
}