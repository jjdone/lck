package simple.lck.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
public class AssistantCoach {

    @Id @GeneratedValue
    @Column(name = "coach_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(nullable = false)
    private String name;

    //생성 메서드
    public static List<AssistantCoach> createAssistantCoach(List<String> assistantCoachNames) {
        List<AssistantCoach> assistantCoaches = new ArrayList<>();
        for (String assistantCoachName : assistantCoachNames) {
            if (assistantCoachName != "" && assistantCoachName != null) {
                AssistantCoach assistantCoach = new AssistantCoach();
                assistantCoach.setName(assistantCoachName);
                assistantCoaches.add(assistantCoach);
            }
        }
        return assistantCoaches;
    }
}
