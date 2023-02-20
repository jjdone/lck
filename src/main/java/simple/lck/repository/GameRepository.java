package simple.lck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import simple.lck.domain.Game;
import simple.lck.domain.GameTeam;
import simple.lck.dto.game.GameListDto;
import simple.lck.dto.game.GameTeamDto;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    @Query(value = "select gt from GameTeam gt where gt.game.id = :gameId")
    List<GameTeam> findGameDetails(@Param("gameId") Long gameId);

    @Query(value = "select new simple.lck.dto.game.GameTeamDto(t.team, t.point) from GameTeam t where t.game.id = :gameId")
    List<GameTeamDto> findGameTeams(@Param("gameId") Long gameId);

    @Query(value = "select new simple.lck.dto.game.GameListDto(g.id, g.gameState, d.round, d.season, t.team, t.point) " +
            "from GameTeam t join t.game g join g.date d " +
            "order by d.startDate ")
    List<GameListDto> findGameList();
}
