package net.k9connect.k9connect.controllers;

import antlr.StringUtils;
import com.fasterxml.jackson.databind.util.JSONPObject;
import net.k9connect.k9connect.models.Dog;
import net.k9connect.k9connect.models.User;
import net.k9connect.k9connect.repositories.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private final UserRepository userDao;

    public HomeController(UserRepository userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/")
    public String welcome() {
        return "index";
    }

    @GetMapping("/about")
    public String goToAboutPage() {
        return "about";
    }

    @GetMapping("/contact")
    public String goToContactPage() {
        return "contact";
    }

    // Get user location info as JSON
    @RequestMapping(value = "/users.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> userLocations(@RequestParam String breed) {
        List<User> allUsers = userDao.findAll();

        if (breed == null || breed.isEmpty()) {
            return allUsers;
        }

        List<User> filteredUsers = new ArrayList<>();

        for (User user : allUsers) {
            List<Dog> dogs = user.getDogs();

            for (Dog dog : dogs) {
                String dogBreed = dog.getBreed().toLowerCase();
                String searchBreed = breed.toLowerCase();

                if (dogBreed.contains(searchBreed)) {
                    filteredUsers.add(user);
                    break;
                }
            }
        }

        return filteredUsers;
    }
}
