package net.k9connect.k9connect.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


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
}
