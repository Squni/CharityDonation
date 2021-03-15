package pl.coderslab.charity.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.entity.VerificationToken;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.repository.VerificationTokenRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final EmailService emailService;
    private final VerificationTokenRepository verificationTokenRepository;


    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository, EmailService emailService, VerificationTokenRepository verificationTokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.emailService = emailService;
        this.verificationTokenRepository = verificationTokenRepository;
    }

    public boolean register(Model model, User user, String confirm, BindingResult result) throws IOException {
        boolean check = true;
        if (result.hasErrors()) {
            check = false;
        }
        if (!user.getPassword().equals(confirm)) {
            check = false;
            model.addAttribute("confirm", "Password doesn't match confirmation.");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            check = false;
            model.addAttribute("exists", "User already exists");
        }
        if (check) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Role userRole = roleRepository.findByName("ROLE_USER");
            user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
            userRepository.save(user);
            generateToken(user);
            sendMail(user, "Activation link", generateVerificationLink(user));
        }
        return check;
    }

    public boolean verifyUser(String id, String token) {
        boolean check = false;
        User user = userRepository.findById(Long.parseLong(id)).get();
        if (verifyToken(token, user)) {
            user.setEnabled(1);
            userRepository.save(user);
            check = true;
        }
        return check;
    }

    public void edit(User user, String password, String passConf) {
        User oldUser = userRepository.findById(user.getId()).get();
        if (!password.isBlank() && password.equals(passConf)) {
            user.setPassword(passwordEncoder.encode(password));
        } else {
            user.setPassword(oldUser.getPassword());
        }
        user.setEmail(oldUser.getEmail());
        userRepository.save(user);
    }

    public boolean sendPasswordResetLink(String email) {
        if (userRepository.existsByEmail(email)) {
            User user = userRepository.findByEmail(email);
            generateToken(user);
            sendMail(user, "Reset your password", generatePasswordResetLink(user));
            return true;
        }
        return false;
    }

    public boolean resetPassword(String token, String id) {
        User user = userRepository.findById(Long.parseLong(id)).get();
        if (verifyToken(token, user)) {
            return true;
        }
        return false;

    }

    public boolean changePass(String password, String passConf, String id) {
        if (!password.isBlank() && password.equals(passConf)) {
            User user = userRepository.findById(Long.parseLong(id)).get();
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    private boolean verifyToken(String token, User user) {
        VerificationToken verificationToken = verificationTokenRepository.findByUser(user);
        if (token.equals(verificationToken.getToken())) {
            verificationTokenRepository.delete(verificationToken);
            return true;
        }
        return false;
    }

    private void sendMail(User user, String message, String link) {
        emailService.sendSimpleMessage(user.getEmail(), message, link);
    }

    private String generateVerificationLink(User user) {
        String token = verificationTokenRepository.findByUser(user).getToken();
        String domain = "http://localhost:8080/activate?t=";
        String verificationLink = domain.concat(token).concat("&id=").concat(user.getId().toString());
        return verificationLink;
    }

    private String generatePasswordResetLink(User user) {
        String token = verificationTokenRepository.findByUser(user).getToken();
        String domain = "http://localhost:8080/new-pass?t=";
        String verificationLink = domain.concat(token).concat("&id=").concat(user.getId().toString());
        return verificationLink;
    }

    private void generateToken(User user) {
        VerificationToken token = new VerificationToken();
        token.setUser(user);
        verificationTokenRepository.save(token);
    }


}
