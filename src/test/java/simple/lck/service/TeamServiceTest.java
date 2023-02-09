package simple.lck.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import simple.lck.domain.Team;
import simple.lck.dto.TeamUpdateScoreDto;
import simple.lck.repository.TeamRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@Transactional
@SpringBootTest
//@Rollback(value = false)
class TeamServiceTest {

    @Autowired TeamRepository teamRepository;
    @Autowired TeamService teamService;

    private Team team;
    private List<String> assistantCoachNames;

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
        assistantCoachNames = Arrays.asList("coach1", "coach2", null);
    }

    @Test
    public void addTeamTest() throws Exception {
        //given

        //when
        Long saveId = teamService.addTeam(team, assistantCoachNames);
        //then
        assertThat(team).isEqualTo(teamRepository.findById(saveId).get());
    }

    @Test
    public void updateScoreTest() throws Exception {
        //given
        Long saveId = teamService.addTeam(team, assistantCoachNames);
        TeamUpdateScoreDto teamUpdateScoreDto = new TeamUpdateScoreDto();
        teamUpdateScoreDto.setWon(2); // 2:0, 2:1
        teamUpdateScoreDto.setLost(1); // 1:2
        teamUpdateScoreDto.setScore(2);
        //when
        Long updateId = teamService.updateScore(saveId, teamUpdateScoreDto);
        //then
        assertThat(teamRepository.findById(updateId).get().getWon()).isEqualTo(2);
        assertThat(teamRepository.findById(updateId).get().getLost()).isEqualTo(1);
        assertThat(teamRepository.findById(updateId).get().getScore()).isEqualTo(2);
    }

    @Test
    public void deleteTeamTest() throws Exception {
        //given
        Long saveId = teamService.addTeam(team, assistantCoachNames);
        Team findTeam = teamRepository.findById(saveId).get();
        //when
        Long deleteId = teamService.deleteTeam(findTeam);
        //then
        assertThat(teamRepository.findById(deleteId)).isEqualTo(Optional.empty());
    }

    @Test
    public void findTeamsTest() throws Exception {
        //given
        teamService.addTeam(team, assistantCoachNames);
        //when
        List<Team> teams = teamService.findTeams();
        //then
        assertThat(teams.size()).isEqualTo(1);
    }
}