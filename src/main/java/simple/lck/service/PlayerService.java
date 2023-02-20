package simple.lck.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simple.lck.domain.Player;
import simple.lck.domain.Team;
import simple.lck.dto.player.PlayerDto;
import simple.lck.repository.PlayerRepository;
import simple.lck.repository.TeamRepository;

import java.util.List;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    // 선수 등록
    @Transactional
    public Long addPlayer(Player player) {
        validateDuplicatePlayer(player);
        playerRepository.save(player);
        return player.getId();
    }

    private void validateDuplicatePlayer(Player player) {
        List<Player> findPlayers = playerRepository.findAllByNickname(player.getNickname());
        if (!findPlayers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 선수 삭제
    @Transactional
    public Long deletePlayer(Long playerId) {
        Player player = playerRepository.findById(playerId).get();
        validateNonexistentPlayer(player);
        playerRepository.delete(player);
        return player.getId();
    }

    private void validateNonexistentPlayer(Player player) {
        List<Player> findPlayers = playerRepository.findAllByNickname(player.getNickname());
        if (findPlayers.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }
    }

    // 선수 상세 조회
    public Player findOne(Long playerId) {
        return playerRepository.findById(playerId).get();
    }

    // 모든 선수 조회
    public List<Player> findPlayers() {
        return playerRepository.findAll();
    }


    // 특정 팀의 선수 목록 조회
    @Transactional
    public List<Player> findPlayersOfTeam(Long teamId) {
        return playerRepository.findPlayersOfTeam(teamId);
    }

    // POG Point 순서로 리스트 조회
    @Transactional
    public List<Player> findPlayersByPogPoint() {
        return playerRepository.findPlayersByPogPoint();
    }

    // Player 업데이트
    @Transactional
    public void updatePlayer(Long playerId, Long teamId, int pogPoint) {
        Team team = teamRepository.findById(teamId).get();
        playerRepository.updatePlayer(team, pogPoint, playerId);
    }

    @Transactional(readOnly = true)
    public List<PlayerDto> findPlayerDtoOfTeamList(Long teamId) {
        List<Player> playersOfTeam = playerRepository.findPlayersOfTeam(teamId);
        return playersOfTeam.stream()
                .map(player -> new PlayerDto(player))
                .collect(toList());
    }
}
