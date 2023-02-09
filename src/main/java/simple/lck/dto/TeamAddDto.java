package simple.lck.dto;

import lombok.Data;

@Data
public class TeamAddDto {

    private Long teamId;
    private String name;
    private String headCoach;
    private int won;
    private int lost;
    private int score;
    private String detailUrl;
    private String assistantCoach1;
    private String assistantCoach2;
    private String assistantCoach3;
}
