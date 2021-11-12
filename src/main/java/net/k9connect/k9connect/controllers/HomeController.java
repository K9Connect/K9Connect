package net.k9connect.k9connect.controllers;

import antlr.StringUtils;
import com.fasterxml.jackson.databind.util.JSONPObject;
import net.k9connect.k9connect.models.Dog;
import net.k9connect.k9connect.models.User;
import net.k9connect.k9connect.repositories.UserRepository;

import net.k9connect.k9connect.services.EmailService;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private final UserRepository userDao;
    private final EmailService emailService;

    @Value("${MAPBOX_API_KEY}")
    private String mapboxKey;

    public HomeController(UserRepository userDao, EmailService emailService) {

        this.userDao = userDao;
        this.emailService = emailService;
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

    @PostMapping("/contact")
    public String sendContactEmail(@RequestParam String subject,@RequestParam String name, @RequestParam String email, @RequestParam String message) {
        System.out.println("hello world");

        String body= "Client Name :"+ name + "\n Client Email:"+email + "\nYour Message:"+ message;
        emailService.prepareAndSend(subject,body,email);
        return "redirect:/contact?success";
    }

    // Get user location info as JSON
    @RequestMapping(value = "/users.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> userLocations(@RequestParam String breed) {
        List<User> allUsers = userDao.findAll();
        List<User> usersWithDogs = new ArrayList<>();

        for (User user : allUsers) {
            List<Dog> dogs = user.getDogs();
            if (dogs != null && !dogs.isEmpty()) {
                usersWithDogs.add(user);
            }
        }

        if (breed == null || breed.isEmpty()) {
            return usersWithDogs;
        }

        List<User> filteredUsers = new ArrayList<>();

        for (User user : usersWithDogs) {
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

    @GetMapping(path = "/keys.js", produces = "application/javascript")
    @ResponseBody
    public String apiKey() {
        return "const mapboxKey = '" + mapboxKey + "'";
    }

    @GetMapping("/map")
    public String showMap() {
        return "map";
    }
}
