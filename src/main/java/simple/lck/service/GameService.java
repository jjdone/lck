package simple.lck.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simple.lck.configuration.GameState;
import simple.lck.domain.Date;
import simple.lck.domain.Game;
import simple.lck.domain.GameTeam;
import simple.lck.dto.game.*;
import simple.lck.repository.GameRepository;
import simple.lck.repository.PlayerRepository;
import simple.lck.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;

import static simple.lck.configuration.GameState.*;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    // 게임 일정 추가
    @Transactional
    public Long addGame(GameAddDto gameAddDto) {

        Date date = Date.builder()
                .season(gameAddDto.getSeason())
                .round(gameAddDto.getRound())
                .startDate(gameAddDto.getStartDate())
                .build();

        GameTeam gameTeam1 = GameTeam.builder()
                .team(teamRepository.findById(gameAddDto.getTeam1Id()).get())
                .top(playerRepository.findById(gameAddDto.getTop1Id()).get())
                .jgl(playerRepository.findById(gameAddDto.getJgl1Id()).get())
                .mid(playerRepository.findById(gameAddDto.getMid1Id()).get())
                .bot(playerRepository.findById(gameAddDto.getBot1Id()).get())
                .spt(playerRepository.findById(gameAddDto.getSpt1Id()).get())
                .point(gameAddDto.getTeam1_point())
                .build();

        GameTeam gameTeam2 = GameTeam.builder()
                .team(teamRepository.findById(gameAddDto.getTeam2Id()).get())
                .top(playerRepository.findById(gameAddDto.getTop2Id()).get())
                .jgl(playerRepository.findById(gameAddDto.getJgl2Id()).get())
                .mid(playerRepository.findById(gameAddDto.getMid2Id()).get())
                .bot(playerRepository.findById(gameAddDto.getBot2Id()).get())
                .spt(playerRepository.findById(gameAddDto.getSpt2Id()).get())
                .point(gameAddDto.getTeam2_point())
                .build();

        Game game = Game.createGame(date, gameTeam1, gameTeam2);
        gameRepository.save(game);

        return game.getId();
    }

    // 게임 일정 조회
    @Transactional
    public List<GameScheduleDto> findGames() {
        List<GameListDto> resultList = gameRepository.findGameList();
        List<GameScheduleDto> gameList = new ArrayList<>();

        for (int i = 0; i < resultList.size(); i += 2) {
            GameScheduleDto gameScheduleDto = GameScheduleDto.builder()
                    .game(gameRepository.findById(resultList.get(i).getId()).get())
                    .gameState(resultList.get(i).getGameState())
                    .round(resultList.get(i).getRound())
                    .season(resultList.get(i).getSeason())
                    .team1(resultList.get(i).getTeam())
                    .team2(resultList.get(i + 1).getTeam())
                    .team1_point(resultList.get(i).getPoint())
                    .team2_point(resultList.get(i + 1).getPoint())
                    .startDate(resultList.get(i).getStartDate())
                    .build();
            gameList.add(gameScheduleDto);
        }

        return gameList;
    }

    // 한 경기 정보
    @Transactional
    public List<GameTeam> findGameDetails(Long gameId) {
        return gameRepository.findGameDetails(gameId);
    }

    // 경기를 업데이트할 정보 가져오기
    @Transactional
    public GameTeamUpdateDto findGameTeams(Long gameId) {
        List<GameTeamDto> findDto = gameRepository.findGameTeams(gameId);

        GameState findGameState = gameRepository.findById(gameId).get().getGameState();

        GameTeamDto team1 = findDto.get(0);
        GameTeamDto team2 = findDto.get(1);

        return GameTeamUpdateDto.builder()
                .team1(team1.getTeam())
                .point1(team1.getPoint())
                .team2(team2.getTeam())
                .point2(team2.getPoint())
                .gameState(findGameState)
                .build();
    }

    //경기 점수 업데이트
    @Transactional
    public void updateGameTeam(Long gameId, GameTeamUpdateDto gameTeamUpdateDto) {
        Game findGame = gameRepository.findById(gameId).get();
        findGame.updatePoint(gameTeamUpdateDto.getTeam1(), gameTeamUpdateDto.getPoint1(),
                gameTeamUpdateDto.getTeam2(), gameTeamUpdateDto.getPoint2(),
                gameTeamUpdateDto.getGameState());
    }

    @Transactional
    public void deleteGame(Long gameId) {
        gameRepository.deleteById(gameId);
    }

    // ------- api -------

    // 메인(오늘의 경기) 정보 조회
    @Transactional
    public GameBeforeDto findMain() {
        List<GameListDto> playingGameList = gameRepository.findPlayingGameList(PLAYING);

        if (!playingGameList.isEmpty()) {
            return new GameBeforeDto(playingGameList.get(0), playingGameList.get(1));
        }

        List<GameListDto> beforeGameList = gameRepository.findBeforeGameList(BEFORE_GAME);
        return new GameBeforeDto(beforeGameList.get(0), beforeGameList.get(1));
    }

    // 경기 리스트 조회
    @Transactional
    public List<GameDto> findGameDtoList() {
        List<GameListDto> resultList = gameRepository.findGameList();
        List<GameDto> gameDtoList = new ArrayList<>();

        for (int i = 0; i < resultList.size(); i += 2) {
            GameDto gameDto = new GameDto(resultList.get(i), resultList.get(1));
            gameDtoList.add(gameDto);
        }

        return gameDtoList;
    }
}
