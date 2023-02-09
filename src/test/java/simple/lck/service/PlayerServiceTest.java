package simple.lck.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import simple.lck.configuration.Position;
import simple.lck.domain.Player;
import simple.lck.domain.Team;
import simple.lck.repository.PlayerRepository;
import simple.lck.repository.TeamRepository;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class PlayerServiceTest {

    @Autowired PlayerService playerService;
    @Autowired PlayerRepository playerRepository;
    @Autowired TeamRepository teamRepository;

    private Team team;
    private Player player;

    @BeforeEach
    public void setUp() {
        team = Team.builder()
                .name("T1")
                .headCoach("배성웅")
                .won(0)
                .lost(0)
                .score(0)
                .detailUrl("http")
                .build();

        teamRepository.save(team);

        player = Player.builder()
                .name("Lee")
                .nickname("Faker")
                .position(Position.MID)
                .team(team)
                .detailUrl("https")
                .build();
    }

    @Test
    public void addPlayerTest() throws Exception {
        //given

        //when
        Long saveId = playerService.addPlayer(player);

        //then
        assertThat(player).isEqualTo(playerRepository.findById(saveId).get());
    }

    @Test
    public void deletePlayerTest() throws Exception {
        //given
        Long saveId = playerService.addPlayer(player);
        Player findPlayer = playerRepository.findById(saveId).get();
        //when
        Long deleteId = playerService.deletePlayer(findPlayer);
        //then
        assertThat(playerRepository.findById(deleteId)).isEqualTo(Optional.empty());
    }
}