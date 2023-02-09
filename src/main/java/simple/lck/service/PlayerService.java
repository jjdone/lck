package simple.lck.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simple.lck.domain.Player;
import simple.lck.repository.PlayerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

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

    @Transactional
    public void deletePlayer(Player player) {
        validateNonexistentPlayer(player);
        playerRepository.delete(player);
    }

    private void validateNonexistentPlayer(Player player) {
        List<Player> findPlayers = playerRepository.findAllByNickname(player.getNickname());
        if (findPlayers.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }
    }
}
