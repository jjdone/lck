package simple.lck.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import simple.lck.domain.AssistantCoach;
import simple.lck.domain.Team;
import simple.lck.dto.team.TeamUpdateScoreDto;
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
        TeamUpdateScoreDto teamUpdateScoreDto = TeamUpdateScoreDto.builder()
                .won(2)
                .lost(1)
                .score(2)
                .build();
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
        Long deleteId = teamService.deleteTeam(findTeam.getId());
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

    @Test
    public void findOneTest() throws Exception {
        //given
        Long saveId = teamService.addTeam(team, assistantCoachNames);
        Team saveTeam = teamRepository.findById(saveId).get();
        //when
        Team findTeam = teamService.findOne(saveId);
        //then
        assertThat(findTeam).isEqualTo(saveTeam);
    }

    @Test
    public void 특정_팀_어시_찾기_Test() throws Exception {
        //given
        Long saveId = teamService.addTeam(team, assistantCoachNames);
        //when
        List<AssistantCoach> assistantCoaches = teamService.findAssistantCoaches(saveId);
        //then
        System.out.println(assistantCoaches.size());
        assertThat(assistantCoaches.get(0).getName()).isEqualTo(assistantCoachNames.get(0));
        assertThat(assistantCoaches.get(1).getName()).isEqualTo(assistantCoachNames.get(1));

    }

    @Test
    public void findTeamRankTest() throws Exception {
        //given
        Team team2 = Team.builder()
                .name("T2")
                .headCoach("배성웅")
                .won(0)
                .lost(0)
                .score(2)
                .detailUrl("http")
                .build();
        List<String> assistantCoachNames2 = Arrays.asList("coach1", "coach2", null);
        Long saveId1 = teamService.addTeam(team, assistantCoachNames);
        Long saveId2 = teamService.addTeam(team2, assistantCoachNames2);
        //when
        List<Team> findTeamRank = teamService.findTeamRank();
        //then
        assertThat(findTeamRank.size()).isEqualTo(2);
        assertThat(findTeamRank.get(0).getName()).isEqualTo("T1");
        assertThat(findTeamRank.get(1).getName()).isEqualTo("T2");
    }
}