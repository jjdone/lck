package simple.lck.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simple.lck.dto.game.GameDto;
import simple.lck.dto.game.GameScheduleDto;
import simple.lck.service.GameService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/games")
public class GameApiController {

    private final GameService gameService;

    @GetMapping
    public ResponseEntity<?> gameList() {
        List<GameDto> games = gameService.findGameDtoList();
        return ResponseEntity.ok(games);
    }
}
