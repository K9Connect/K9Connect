package net.k9connect.k9connect.controllers;

import net.k9connect.k9connect.models.*;
import net.k9connect.k9connect.repositories.DogRepository;
import net.k9connect.k9connect.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    private UserRepository userDao;
    private DogRepository dogDao;
    private PasswordEncoder passwordEncoder;

    public AdminController(UserRepository userDao, DogRepository dogDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.dogDao = dogDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/users/all")
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

    @PostMapping("/users/all")
    public String updateUserStatus(
            @RequestParam(name = "user-id") long userId,
            @RequestParam(name = "user-status") Statuses status
    ) {
        User user = userDao.getById(userId);
        System.out.println("User: " + user.getUsername());
        user.setStatus(status);
        userDao.save(user);

        return "redirect:/users/all";
    }

    @GetMapping("/dogs/all")
    public String showAllDogs(Model model) {
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInDb = userDao.getById(authenticatedUser.getId());

        if (userInDb.is_admin()) {
            List<Dog> dogs = dogDao.findAll();

            model.addAttribute("dogs", dogs);

            return "dogs/all";
        }

        return "redirect:/4xx";
    }
}
