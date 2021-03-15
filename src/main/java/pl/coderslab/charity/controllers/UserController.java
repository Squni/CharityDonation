package pl.coderslab.charity.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.entity.CurrentUser;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("activate")
    public String activateAccount(@RequestParam String t, @RequestParam String id) {
        userService.verifyUser(id, t);
        return "redirect:login";
    }

    @GetMapping("/app/edit")
    public String editUser(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        model.addAttribute("user", userRepository.findById(currentUser.getUser().getId()).get());
        return "edit";
    }

    @PostMapping("/app/edit")
    public String editUserSave(@Valid User user, BindingResult result, @RequestParam String password,
                               @RequestParam String passConf) {
        if (!result.hasErrors()) {
            userService.edit(user, password, passConf);
            return "redirect:edit";
        }
        return "edit";
    }

    @GetMapping("reset")
    public String passwordResetForm() {
        return "resetForm";
    }

    @PostMapping("reset")
    public String passwordResetEmail(@RequestParam String email, Model model) {
        if (userService.sendPasswordResetLink(email)) {
            return "resetMailSent";
        }
        model.addAttribute("notFound", "Brak użytkownika o podanym adresie email");
        return "resetForm";
    }

    @GetMapping("new-pass")
    public String newPassForm(@RequestParam String t, @RequestParam String id, Model model) {
        if (userService.resetPassword(t, id)) {
            model.addAttribute("id", id);
            return "newPass";
        }
        return "tokenInvalid";
    }

    @PostMapping("new-pass")
    public String newPassSave(@RequestParam String password, @RequestParam String passConf,
                              @RequestParam String id, Model model) {
        if (userService.changePass(password, passConf, id)) {
            return "passChangedConf";
        }
        model.addAttribute("passInvalid", "Niepoprawne hasło");
        return "newPass";
    }
}
