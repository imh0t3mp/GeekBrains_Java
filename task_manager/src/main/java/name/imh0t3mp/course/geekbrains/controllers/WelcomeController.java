package name.imh0t3mp.course.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class WelcomeController {

    @Value("${welcome.message}")
    private String message;

    private List<String> tasks = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

    @GetMapping("/welcome")
    public String welcome(Model model) {
        model.addAttribute("message", message);
        model.addAttribute("tasks.html", tasks);

        return "welcome";
    }

    // /hello?name=V.V.Putin
    @GetMapping("/welcome/hello")
    public String helloWithParam(
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            Model model) {

        model.addAttribute("message", name);

        return "welcome";
    }

}