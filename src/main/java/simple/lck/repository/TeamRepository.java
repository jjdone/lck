package simple.lck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.lck.domain.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findAllByName(String teamName);
}
