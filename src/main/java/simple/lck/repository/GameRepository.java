package simple.lck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.lck.domain.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
}
