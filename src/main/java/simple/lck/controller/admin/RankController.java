package simple.lck.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import simple.lck.domain.Team;
import simple.lck.service.TeamService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RankController {

    private final TeamService teamService;

    @GetMapping("/admin/rank")
    public String rankList(Model model) {
        List<Team> rank = teamService.findTeamRank();
        model.addAttribute("rank", rank);
        return "admin/rank/rankList";
    }
}
