package simple.lck.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import simple.lck.configuration.Season;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Date {

    @Id @GeneratedValue
    @Column(name = "date_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Season season;

    private String round;
    private LocalDateTime startDate;

    @Builder
    public Date(Season season, String round, LocalDateTime startDate) {
        this.season = season;
        this.round = round;
        this.startDate = startDate;
    }
}
