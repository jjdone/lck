package simple.lck.domain;

import lombok.Getter;
import simple.lck.configuration.Season;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Date {

    @Id @GeneratedValue
    @Column(name = "date_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Season season;

    private String round;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
