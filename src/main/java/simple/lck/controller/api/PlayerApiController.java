package simple.lck.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simple.lck.dto.player.PlayerDto;
import simple.lck.service.PlayerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/players")
public class PlayerApiController {

    private final PlayerService playerService;

    @GetMapping
    public ResponseEntity<?> playerList() {
        List<PlayerDto> playerDtoList = playerService.findPlayerDtoList();
        return ResponseEntity.ok(playerDtoList);
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<?> playerDetails(@PathVariable Long playerId) {
        PlayerDto playerDto = playerService.findPlayerDto(playerId);
        return ResponseEntity.ok(playerDto);
    }

    @GetMapping("/rank")
    public ResponseEntity<?> playerRank() {
        List<PlayerDto> playerRank = playerService.findPlayerRank();
        return ResponseEntity.ok(playerRank);
    }
}