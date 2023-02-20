package simple.lck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import simple.lck.domain.Player;
import simple.lck.domain.Team;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findAllByNickname(String nickname);

    @Modifying(clearAutomatically = true)
    @Query(value = "update Player p set p.team = :team, p.pogPoint = :pogPoint where p.id = :playerId")
    int updatePlayer(@Param("team") Team team, @Param("pogPoint") int pogPoint, @Param("playerId") Long playerId);

    @Query(value = "select p from Player p where p.team.id = :teamId")
    List<Player> findPlayersOfTeam(@Param("teamId") Long teamId);

    @Query(value = "select p from Player p order by p.pogPoint desc")
    List<Player> findPlayersByPogPoint();
}
