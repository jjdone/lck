package simple.lck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping({"/", "/main"})
    public String mainForm() {
        return "main";
    }
}
