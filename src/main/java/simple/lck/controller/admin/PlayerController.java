package simple.lck.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import simple.lck.configuration.Position;
import simple.lck.domain.Player;
import simple.lck.domain.Team;
import simple.lck.dto.player.PlayerAddDto;
import simple.lck.service.PlayerService;
import simple.lck.service.TeamService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/players")
public class PlayerController {

    private final PlayerService playerService;
    private final TeamService teamService;

    @GetMapping("/addPlayerForm")
    public String addPlayerForm(Model model) {
        List<Team> teams = teamService.findTeams();

        model.addAttribute("teams", teams);
        model.addAttribute("form", new Player());
        return "admin/players/addPlayerForm";
    }

    @ModelAttribute("position")
    public Position[] position() {
        return Position.values();
    }

    @PostMapping("/addPlayerForm")
    public String addPlayer(@ModelAttribute PlayerAddDto playerAddDto) {
        Team team = teamService.findOne(playerAddDto.getTeamId());
        Player player = Player.builder()
                .name(playerAddDto.getName())
                .nickname(playerAddDto.getNickname())
                .position(playerAddDto.getPosition())
                .team(team)
                .pogPoint(playerAddDto.getPogPoint())
                .detailUrl(playerAddDto.getDetailUrl())
                .build();
        playerService.addPlayer(player);
        return "redirect:/admin/players";
    }

    @GetMapping
    public String playerList(Model model) {
        List<Player> players = playerService.findPlayers();
        model.addAttribute("players", players);
        return "admin/players/playerList";
    }

    @GetMapping("/{playerId}")
    public String playerDetail(@PathVariable Long playerId, Model model) {
        Player player = playerService.findOne(playerId);
        model.addAttribute("player", player);
        return "admin/players/playerDetail";
    }

    @GetMapping("/{playerId}/delete")
    public String deletePlayer(@PathVariable Long playerId) {
        playerService.deletePlayer(playerId);
        return "redirect:/admin/players";
    }

    @GetMapping("/pogRank")
    public String pogRank(Model model) {
        List<Player> playersByPogPoint = playerService.findPlayersByPogPoint();
        model.addAttribute("players", playersByPogPoint);
        return "admin/players/pogRank";
    }

    @GetMapping("/{playerId}/updatePlayer")
    public String updatePlayerForm(@PathVariable Long playerId, Model model) {
        Player player = playerService.findOne(playerId);
        List<Team> teams = teamService.findTeams();

        model.addAttribute("player", player);
        model.addAttribute("teams", teams);
        return "admin/players/updatePlayerForm";
    }

    @PostMapping("/{playerId}/updatePlayer")
    public String updatePlayer(@PathVariable Long playerId,
                               @RequestParam("teamId") Long teamId,
                               @RequestParam("pogPoint") int pogPoint) {
        playerService.updatePlayer(playerId, teamId, pogPoint);
        return "redirect:/admin/players/{playerId}";
    }
}
