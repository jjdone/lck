package simple.lck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import simple.lck.domain.Player;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findAllByNickname(String nickname);
}
