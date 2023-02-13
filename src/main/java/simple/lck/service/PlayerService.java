package simple.lck.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simple.lck.domain.Player;
import simple.lck.repository.PlayerRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final EntityManager em;

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
    public Long deletePlayer(Player player) {
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
        String query = "select p from Player p where p.team.id = :teamId";
        return em.createQuery(query, Player.class)
                .setParameter("teamId", teamId)
                .getResultList();
    }
}
