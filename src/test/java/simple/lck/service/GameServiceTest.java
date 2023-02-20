package simple.lck.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import simple.lck.configuration.GameState;
import simple.lck.domain.*;
import simple.lck.dto.game.GameAddDto;
import simple.lck.dto.game.GameScheduleDto;
import simple.lck.dto.game.GameTeamUpdateDto;
import simple.lck.repository.GameRepository;
import simple.lck.repository.PlayerRepository;
import simple.lck.repository.TeamRepository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static simple.lck.configuration.GameState.END;
import static simple.lck.configuration.Position.MID;
import static simple.lck.configuration.Season.*;

@Transactional
@SpringBootTest
//@Rollback(value = false)
class GameServiceTest {

    @Autowired GameService gameService;
    @Autowired GameRepository gameRepository;
    @Autowired TeamRepository teamRepository;
    @Autowired PlayerRepository playerRepository;
    @Autowired EntityManager em;

    private GameAddDto gameAddDto;

    @BeforeEach
    public void setUp() {
        Team team = Team.builder()
                .name("T1")
                .headCoach("배성웅")
                .won(0)
                .lost(0)
                .score(0)
                .detailUrl("http")
                .build();

        teamRepository.save(team);

        Player player = Player.builder()
                .name("Lee")
                .nickname("Faker")
                .position(MID)
                .team(team)
                .detailUrl("https")
                .build();

        playerRepository.save(player);

        gameAddDto = GameAddDto.builder()
                .season(SPRING)
                .round("1R")
                .startDate(LocalDateTime.of(2023, 2, 19, 17,0,0))
                .team1Id(team.getId()).top1Id(player.getId()).jgl1Id(player.getId()).mid1Id(player.getId()).bot1Id(player.getId()).spt1Id(player.getId())
                .team2Id(team.getId()).top2Id(player.getId()).jgl2Id(player.getId()).mid2Id(player.getId()).bot2Id(player.getId()).spt2Id(player.getId())
                .team1_point(0)
                .team2_point(0)
                .build();
    }

    @Test
    public void addGameTest() throws Exception {
        //given

        //when
        Long saveId = gameService.addGame(gameAddDto);
        //then
        assertThat(gameRepository.findById(saveId).get().getId()).isEqualTo(saveId);
    }

    @Test
    public void findGamesTest() throws Exception {
        //given
        gameService.addGame(gameAddDto);
        //when
        List<GameScheduleDto> games = gameService.findGames();
        //then
        assertThat(games.size()).isEqualTo(1);
        assertThat(games.get(0).getSeason()).isEqualTo(SPRING);
    }

    @Test
    public void findGameTeamsTest() throws Exception {
        //given
        Long saveId = gameService.addGame(gameAddDto);
        //when
        GameTeamUpdateDto findGameTeams = gameService.findGameTeams(saveId);
        //then
        assertThat(findGameTeams.getTeam1().getName()).isEqualTo("T1");
        assertThat(findGameTeams.getTeam2().getName()).isEqualTo("T1");
        assertThat(findGameTeams.getPoint1()).isEqualTo(0);
        assertThat(findGameTeams.getPoint2()).isEqualTo(0);
        assertThat(findGameTeams.getGameState()).isEqualTo(END);
    }

    @Test
    public void updateGameTeamTest() throws Exception {
        //given
        Long saveId = gameService.addGame(gameAddDto);
        GameTeamUpdateDto findSavedGameTeams = gameService.findGameTeams(saveId);
        //when
        GameTeamUpdateDto gameTeamUpdateDto = GameTeamUpdateDto.builder()
                .gameState(GameState.PLAYING)
                .team1(findSavedGameTeams.getTeam1())
                .point1(1)
                .team2(findSavedGameTeams.getTeam2())
                .point2(2)
                .build();
        gameService.updateGameTeam(saveId, gameTeamUpdateDto);
        //then
        GameTeamUpdateDto findGameTeams = gameService.findGameTeams(saveId);
        assertThat(gameTeamUpdateDto.getGameState()).isEqualTo(findGameTeams.getGameState());
        assertThat(gameTeamUpdateDto.getPoint1()).isEqualTo(findGameTeams.getPoint1());
        assertThat(gameTeamUpdateDto.getPoint2()).isEqualTo(findGameTeams.getPoint2());
    }
}