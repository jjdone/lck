package simple.lck.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simple.lck.dto.game.GameBeforeDto;
import simple.lck.service.GameService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MainApiController {

    private final GameService gameService;

    @GetMapping
    public ResponseEntity<?> main() {
        GameBeforeDto main = gameService.findMain();
        return ResponseEntity.ok(main);
    }
}
