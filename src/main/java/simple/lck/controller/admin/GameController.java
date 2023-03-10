package simple.lck.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import simple.lck.configuration.GameState;
import simple.lck.configuration.Position;
import simple.lck.configuration.Season;
import simple.lck.domain.GameTeam;
import simple.lck.domain.Player;
import simple.lck.domain.Team;
import simple.lck.dto.game.GameAddDto;
import simple.lck.dto.game.GameScheduleDto;
import simple.lck.dto.game.GameTeamUpdateDto;
import simple.lck.service.GameService;
import simple.lck.service.PlayerService;
import simple.lck.service.TeamService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/games")
public class GameController {

    private final GameService gameService;
    private final TeamService teamService;
    private final PlayerService playerService;

    @GetMapping("/addGameForm")
    public String addGameForm(Model model) {
        List<Team> teams = teamService.findTeams();
        List<Player> players = playerService.findPlayers();

        model.addAttribute("players", players);
        model.addAttribute("teams", teams);
        model.addAttribute("form", new GameAddDto());
        return "admin/games/addGameForm";
    }

    @ModelAttribute("season")
    public Season[] seasons() {
        return Season.values();
    }

    @ModelAttribute("positionTop")
    public Position positionTop() {
        return Position.TOP;
    }

    @ModelAttribute("positionJgl")
    public Position positionJgl() {
        return Position.JGL;
    }

    @ModelAttribute("positionMid")
    public Position positionMid() {
        return Position.MID;
    }

    @ModelAttribute("positionBot")
    public Position positionBot() {
        return Position.BOT;
    }

    @ModelAttribute("positionSpt")
    public Position positionSpt() {
        return Position.SPT;
    }

    @PostMapping("/addGameForm")
    public String addGame(@ModelAttribute GameAddDto gameAddDto) {
        System.out.println("gameAddDto.getStartDate() = " + gameAddDto.getStartDate());
        gameService.addGame(gameAddDto);
        return "redirect:/admin/games";
    }

    @GetMapping
    public String gameList(Model model) {
        List<GameScheduleDto> games = gameService.findGames();
        model.addAttribute("games", games);
        return "admin/games/gameList";
    }

    @GetMapping("/{gameId}")
    public String gameDetails(@PathVariable Long gameId, Model model) {
        List<GameTeam> gameDetails = gameService.findGameDetails(gameId);
        model.addAttribute("gameTeams", gameDetails);
        return "admin/games/gameDetail";
    }

    @GetMapping("/{gameId}/deleteGame")
    public String deleteGame(@PathVariable Long gameId) {
        gameService.deleteGame(gameId);
        return "redirect:/admin/games";
    }

    @GetMapping("/{gameId}/updateScore")
    public String updateScoreForm(@PathVariable Long gameId, Model model) {
        GameTeamUpdateDto updateDto = gameService.findGameTeams(gameId);
        model.addAttribute("form", updateDto);
        return "admin/games/updateScoreForm";
    }

    @ModelAttribute("gameStates")
    public GameState[] gameStates() {
        return GameState.values();
    }

    @PostMapping("/{gameId}/updateScore")
    public String updateScore(@PathVariable Long gameId, @ModelAttribute GameTeamUpdateDto gameTeamUpdateDto) {
        gameService.updateGameTeam(gameId, gameTeamUpdateDto);
        return "redirect:/admin/games";
    }
}
