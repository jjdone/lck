package simple.lck.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simple.lck.domain.Date;
import simple.lck.domain.Game;
import simple.lck.domain.GameTeam;
import simple.lck.dto.GameAddDto;
import simple.lck.dto.GameListDto;
import simple.lck.dto.GameScheduleDto;
import simple.lck.repository.GameRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final EntityManager em;

    // 게임 일정 추가
    @Transactional
    public Long addGame(GameAddDto gameAddDto) {

        Date date = Date.builder()
                .season(gameAddDto.getSeason())
                .round(gameAddDto.getRound())
                .startDate(gameAddDto.getStartDate())
                .build();

        GameTeam gameTeam1 = GameTeam.builder()
                .team(gameAddDto.getTeam1())
                .top(gameAddDto.getTop1())
                .jgl(gameAddDto.getJgl1())
                .mid(gameAddDto.getMid1())
                .bot(gameAddDto.getBot1())
                .spt(gameAddDto.getSpt1())
                .point(gameAddDto.getTeam1_point())
                .build();

        GameTeam gameTeam2 = GameTeam.builder()
                .team(gameAddDto.getTeam2())
                .top(gameAddDto.getTop2())
                .jgl(gameAddDto.getJgl2())
                .mid(gameAddDto.getMid2())
                .bot(gameAddDto.getBot2())
                .spt(gameAddDto.getSpt2())
                .point(gameAddDto.getTeam2_point())
                .build();

        Game game = Game.createGame(date, gameTeam1, gameTeam2);
        gameRepository.save(game);

        return game.getId();
    }

    // 게임 일정 조회
    public List<GameScheduleDto> findGames() {
        String query = "select g.id, g.gameState, d.round, d.season, t.team, t.point " +
                "from Game g join Date d join GameTeam t";
        List<GameListDto> resultList = em.createQuery(query, GameListDto.class).getResultList();
        List<GameScheduleDto> gameList = new ArrayList<>();
        for (int i = 0; i < resultList.size(); i += 2) {
            GameScheduleDto gameScheduleDto = GameScheduleDto.builder()
                    .game_id(resultList.get(i).getGame_id())
                    .gameState(resultList.get(i).getGameState())
                    .round(resultList.get(i).getRound())
                    .season(resultList.get(i).getSeason())
                    .team1(resultList.get(i).getTeam())
                    .team2(resultList.get(i+1).getTeam())
                    .team1_point(resultList.get(i).getPoint())
                    .team2_point(resultList.get(i+1).getPoint())
                    .build();
            gameList.add(gameScheduleDto);
        }

        return gameList;
    }
}
