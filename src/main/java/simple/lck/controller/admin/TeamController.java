package simple.lck.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import simple.lck.domain.AssistantCoach;
import simple.lck.domain.Player;
import simple.lck.domain.Team;
import simple.lck.dto.team.TeamAddDto;
import simple.lck.dto.team.TeamUpdateScoreDto;
import simple.lck.service.PlayerService;
import simple.lck.service.TeamService;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/teams")
public class TeamController {

    private final TeamService teamService;
    private final PlayerService playerService;

    @GetMapping("/addTeamForm")
    public String addTeamFrom(Model model) {
        model.addAttribute("form", new TeamAddDto());
        return "admin/teams/addTeamForm";
    }

    @PostMapping("/addTeamForm")
    public String addTeam(TeamAddDto teamAddDto) {
        Team team = Team.builder()
                .name(teamAddDto.getName())
                .headCoach(teamAddDto.getHeadCoach())
                .won(teamAddDto.getWon())
                .lost(teamAddDto.getLost())
                .score(teamAddDto.getScore())
                .detailUrl(teamAddDto.getDetailUrl())
                .build();

        List<String> assistantCoachNames = Arrays.asList(teamAddDto.getAssistantCoach1(),
                teamAddDto.getAssistantCoach2(),
                teamAddDto.getAssistantCoach3());

        teamService.addTeam(team, assistantCoachNames);

        return "redirect:/admin/teams";
    }

    @GetMapping("/{teamId}/delete")
    public String deleteTeam(@PathVariable Long teamId) {
        teamService.deleteTeam(teamId);
        return "redirect:/admin/teams";
    }

    @GetMapping
    public String teamList(Model model) {
        List<Team> teams = teamService.findTeams();

        model.addAttribute("teams", teams);

        return "admin/teams/teamList";
    }

    @GetMapping("/{teamId}")
    public String teamDetail(@PathVariable Long teamId, Model model) {
        Team team = teamService.findOne(teamId);
        List<AssistantCoach> assistantCoaches = teamService.findAssistantCoaches(teamId);
        model.addAttribute("team", team);
        model.addAttribute("assistantCoaches", assistantCoaches);
        return "admin/teams/teamDetail";
    }

    @GetMapping("/{teamId}/updateScore")
    public String updateScoreForm(@PathVariable Long teamId, Model model) {
        Team team = teamService.findOne(teamId);
        TeamUpdateScoreDto form = TeamUpdateScoreDto.builder()
                .won(team.getWon())
                .lost(team.getLost())
                .score(team.getScore())
                .build();

        model.addAttribute("form", form);
        return "admin/teams/updateScoreForm";
    }

    @PostMapping("/{teamId}/updateScore")
    public String updateScore(@PathVariable Long teamId,
                              @ModelAttribute TeamUpdateScoreDto teamUpdateScoreDto) {
        teamService.updateScore(teamId, teamUpdateScoreDto);
        return "redirect:/admin/teams/{teamId}";
    }

    @GetMapping("/{teamId}/players")
    public String players(@PathVariable Long teamId, Model model) {
        List<Player> players = playerService.findPlayersOfTeam(teamId);
        model.addAttribute("players", players);
        return "admin/teams/players";
    }
}
