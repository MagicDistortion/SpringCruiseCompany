package project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController extends ParentController {
    @GetMapping({"/", "/index"})
    public String index(Model model) {
        return "index";
    }
    @GetMapping("/about_us")
    public String aboutUs(Model model) {
        return "about_us";
    }

    @GetMapping("/contacts")
    public String contacts(Model model) {
        return "contacts";
    }

}