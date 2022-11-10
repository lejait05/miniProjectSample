package mini.controllers;


import mini.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import mini.repositories.UserRepository;
import mini.services.EmailService;

@Controller
public class UserController {

    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user, @RequestParam String phoneNumber){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setPhone_number(Long.parseLong(phoneNumber));
        userDao.save(user);
        emailService.prepareAndSend(user, "Thank you for creating a new account!");
        return "redirect:/login";
    }
}
