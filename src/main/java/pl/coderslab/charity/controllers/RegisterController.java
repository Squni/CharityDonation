package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("register")
    public String registerSubmit(@Valid User user, BindingResult result, Model model,
                                 @RequestParam String passConf) throws IOException {
        if (userService.register(model, user, passConf, result)) {
            return "redirect:login";
        }
        return "register";

    }

}
