package name.imh0t3mp.course.geekbrains.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping({"", "/"})
    public String mainPage() {
        return "index";
    }
}
