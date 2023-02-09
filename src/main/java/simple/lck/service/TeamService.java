package simple.lck.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simple.lck.domain.AssistantCoach;
import simple.lck.domain.Team;
import simple.lck.dto.TeamUpdateScoreDto;
import simple.lck.repository.TeamRepository;

import java.util.List;

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
    public Long deleteTeam(Team team) {
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
    public List<Team> findTeams() {
        return teamRepository.findAll();
    }
}
