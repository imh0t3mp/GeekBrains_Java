package name.imh0t3mp.course.geekbrains.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/alloha")
public class AllohaController {
    @GetMapping("/")
    public String alloha(Model model) {
        return "welcome";
    }
}
