package simple.lck.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import simple.lck.dto.TeamUpdateScoreDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Team {

    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
    private String headCoach;
    private int won;
    private int lost;
    private int score;
    private String detailUrl;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<AssistantCoach> assistantCoaches = new ArrayList<>();

    @Builder
    public Team(String name, String headCoach, int won, int lost, int score, String detailUrl) {
        this.name = name;
        this.headCoach = headCoach;
        this.won = won;
        this.lost = lost;
        this.score = score;
        this.detailUrl = detailUrl;
    }

    // 연관관계 메서드
    public void addAssistantCoach(List<AssistantCoach> assistantCoaches) {
        for (AssistantCoach assistantCoach : assistantCoaches) {
            this.assistantCoaches.add(assistantCoach);
            assistantCoach.setTeam(this);
        }
    }

    // 승/페 점수 업데이트
    public void updateScore(TeamUpdateScoreDto teamUpdateScoreDto) {
        this.won = teamUpdateScoreDto.getWon();
        this.lost = teamUpdateScoreDto.getLost();
        this.score = teamUpdateScoreDto.getScore();
    }
}
