package net.k9connect.k9connect.controllers;

import net.k9connect.k9connect.models.*;
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
    private PasswordEncoder passwordEncoder;

    public AdminController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
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
//            List<Dog> dogs = dogDao.findAll(); // uncomment when DogRepo available
            User owner1 = userDao.getById(1L);
            User owner2 = userDao.getById(2L);
            User owner3 = userDao.getById(3L);
            User owner4 = userDao.getById(4L);

            List<Dog> dogs = new ArrayList<>();
            dogs.add(new Dog(
                    1L,
                    "Winky",
                    "Norwich Terrier",
                    'F',
                    owner1,
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    10,
                    Statuses.active,
                    new DogDetails()
                    ));
            dogs.add(new Dog(
                    2L,
                    "Hubert",
                    "Bloodhound",
                    'M',
                    owner2,
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    9,
                    Statuses.active,
                    new DogDetails()
                    ));
            dogs.add(new Dog(
                    3L,
                    "Rhapsody in White",
                    "Standard Poodle",
                    'F',
                    owner3,
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    10,
                    Statuses.active,
                    new DogDetails()
                    ));
            dogs.add(new Dog(
                    4L,
                    "Miss Agnes",
                    "Shih Tzu",
                    'F',
                    owner4,
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    8,
                    Statuses.active,
                    new DogDetails()
            ));

            model.addAttribute("dogs", dogs);

            return "dogs/all";
        }

        return "redirect:/4xx";
    }
}
