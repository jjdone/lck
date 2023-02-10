package simple.lck.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import simple.lck.configuration.GameState;
import simple.lck.configuration.Season;
import simple.lck.domain.*;
import simple.lck.dto.GameAddDto;
import simple.lck.repository.GameRepository;
import simple.lck.repository.PlayerRepository;
import simple.lck.repository.TeamRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static simple.lck.configuration.Position.MID;
import static simple.lck.configuration.Season.*;

@Transactional
@SpringBootTest
@Rollback(value = false)
class GameServiceTest {

    @Autowired GameService gameService;
    @Autowired GameRepository gameRepository;
    @Autowired TeamRepository teamRepository;
    @Autowired PlayerRepository playerRepository;

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
                .startDate(LocalDateTime.of(2023, 2, 20, 17,0,0))
                .team1(team).top1(player).jgl1(player).mid1(player).bot1(player).spt1(player)
                .team2(team).top2(player).jgl2(player).mid2(player).bot2(player).spt2(player)
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
        assertThat(gameRepository.findById(saveId).get().getGameState()).isEqualTo(GameState.BEFORE);
    }
}