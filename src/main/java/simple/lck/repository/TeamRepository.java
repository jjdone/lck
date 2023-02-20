package simple.lck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import simple.lck.domain.AssistantCoach;
import simple.lck.domain.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findAllByName(String teamName);

    @Query(value = "select a from AssistantCoach a where a.team.id = :teamId")
    List<AssistantCoach> findAssistantCoaches(@Param("teamId") Long teamId);

    @Query(value = "select t from Team t order by t.score desc")
    List<Team> findTeamRank();
}
