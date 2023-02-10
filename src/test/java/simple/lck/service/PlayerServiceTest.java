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
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static simple.lck.configuration.Position.*;

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
                .position(MID)
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

    @Test
    public void findOneTest() throws Exception {
        //given
        Long saveId = playerService.addPlayer(player);
        Player savePlayer = playerRepository.findById(saveId).get();
        //when
        Player findPlayer = playerService.findOne(saveId);
        //then
        assertThat(findPlayer).isEqualTo(savePlayer);
    }

    @Test
    public void findPlayersOfTeamTest() throws Exception {
        //given
        Player player2 = Player.builder()
                .name("Choi")
                .nickname("Zeus")
                .position(TOP)
                .team(team)
                .detailUrl("https")
                .build();
        playerService.addPlayer(player);
        playerService.addPlayer(player2);
        Long teamId = team.getId();
        //when
        List<Player> findPlayers = playerService.findPlayersOfTeam(teamId);
        //then
        assertThat(findPlayers.size()).isEqualTo(2);
    }
}