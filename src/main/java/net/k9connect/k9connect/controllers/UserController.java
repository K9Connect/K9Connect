package net.k9connect.k9connect.controllers;

import net.k9connect.k9connect.models.User;
import net.k9connect.k9connect.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/login";
    }

    @Controller
    public class AuthenticationController {
        @GetMapping("/login")
        public String showLoginForm() {
            return "users/login";
        }
    }

    @GetMapping("/all-users")
    public String showAllUsers(Model model) {
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInDb = userDao.getById(authenticatedUser.getId());

        if (userInDb.is_admin()) {
            List<User> users = userDao.findAll();
            model.addAttribute("users", users);

            return "users/all";
        }

        return "redirect:/4xx";
    }
}