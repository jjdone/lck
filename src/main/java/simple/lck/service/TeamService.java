package simple.lck.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simple.lck.domain.AssistantCoach;
import simple.lck.domain.Team;
import simple.lck.dto.team.TeamDetailsDto;
import simple.lck.dto.team.TeamDto;
import simple.lck.dto.team.TeamUpdateScoreDto;
import simple.lck.repository.TeamRepository;

import java.util.List;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    //Team 등록
    @Transactional
    public Long addTeam(Team team, List<String> assistantCoachNames) {
        validateDuplicateTeam(team);

        List<AssistantCoach> assistantCoaches = AssistantCoach.createAssistantCoach(assistantCoachNames);

        team.addAssistantCoach(assistantCoaches);

        teamRepository.save(team);
        return team.getId();
    }

    private void validateDuplicateTeam(Team team) {
        List<Team> findTeams = teamRepository.findAllByName(team.getName());
        if (!findTeams.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 팀입니다.");
        }
    }

    //Team 승/패 득점차 update
    @Transactional
    public Long updateScore(Long teamId, TeamUpdateScoreDto teamUpdateScoreDto) {
        Team findTeam = teamRepository.findById(teamId).get();
        findTeam.updateScore(teamUpdateScoreDto);
        return findTeam.getId();
    }

    // Team 삭제
    @Transactional
    public Long deleteTeam(Long teamId) {
        Team team = teamRepository.findById(teamId).get();
        validateNonexistentTeam(team);
        teamRepository.delete(team);
        return team.getId();
    }

    private void validateNonexistentTeam(Team team) {
        List<Team> findTeams = teamRepository.findAllByName(team.getName());
        if (findTeams.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 팀입니다.");
        }
    }

    // Team 목록 검색
    @Transactional(readOnly = true)
    public List<Team> findTeams() {
        return teamRepository.findAll();
    }

    // Team 검색
    @Transactional(readOnly = true)
    public Team findOne(Long teamId) {
        return teamRepository.findById(teamId).get();
    }

    // 팀의 어시 코치 찾기
    @Transactional
    public List<AssistantCoach> findAssistantCoaches(Long teamId) {
        return teamRepository.findAssistantCoaches(teamId);
    }

    // 팀 정규 순위 리스트
    @Transactional
    public List<Team> findTeamRank() {
        return teamRepository.findTeamRank();
    }

    // ------- api function -------

    //팀 리스트
    @Transactional(readOnly = true)
    public List<TeamDto> findTeamDtoList() {
        List<Team> teams = teamRepository.findAll();
        return teams.stream()
                .map(TeamDto::new)
                .collect(toList());
    }

    // 팀 상세
    @Transactional
    public TeamDetailsDto findTeamDetailsDto(Long teamId) {
        Team team = teamRepository.findById(teamId).get();
        List<AssistantCoach> assistantCoaches = teamRepository.findAssistantCoaches(teamId);

        return new TeamDetailsDto(team, assistantCoaches);
    }

    // 팀 정규 순위
    @Transactional
    public List<TeamDto> findTeamDtoRank() {
        List<Team> teamRank = teamRepository.findTeamRank();
        return teamRank.stream()
                .map(TeamDto::new)
                .collect(toList());
    }
}
