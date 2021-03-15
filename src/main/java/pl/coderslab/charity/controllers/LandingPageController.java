package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.charity.service.InstitutionService;

@Controller
public class LandingPageController {

    @GetMapping("")
    public String home(Model model) {
        return "redirect:home";
    }


}
