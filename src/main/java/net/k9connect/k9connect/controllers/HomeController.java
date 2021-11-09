package net.k9connect.k9connect.controllers;

import net.k9connect.k9connect.models.User;
import net.k9connect.k9connect.repositories.UserRepository;
import net.k9connect.k9connect.services.EmailService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {
    private final UserRepository userDao;
    private final EmailService emailService;

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
    public List<User> userLocations() {
        return userDao.findAll();
    }
}
