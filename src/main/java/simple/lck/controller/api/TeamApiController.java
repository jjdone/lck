package simple.lck.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simple.lck.dto.player.PlayerDto;
import simple.lck.dto.team.TeamDetailsDto;
import simple.lck.dto.team.TeamDto;
import simple.lck.service.PlayerService;
import simple.lck.service.TeamService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teams")
public class TeamApiController {

    private final TeamService teamService;
    private final PlayerService playerService;

    @GetMapping
    public ResponseEntity<?> teamList() {
        List<TeamDto> teams = teamService.findTeamDtoList();
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<?> teamDetails(@PathVariable Long teamId) {
        TeamDetailsDto teamDetailsDto = teamService.findTeamDetailsDto(teamId);
        return ResponseEntity.ok(teamDetailsDto);
    }

    @GetMapping("/{teamId}/players")
    public ResponseEntity<?> playersOfTeam(@PathVariable Long teamId) {
        List<PlayerDto> playerDtoOfTeamList = playerService.findPlayerDtoOfTeamList(teamId);
        return ResponseEntity.ok(playerDtoOfTeamList);
    }
}
