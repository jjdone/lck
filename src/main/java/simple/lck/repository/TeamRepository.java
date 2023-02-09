package simple.lck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.lck.domain.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
