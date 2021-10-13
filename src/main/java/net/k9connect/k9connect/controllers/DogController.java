package net.k9connect.k9connect.controllers;


import net.k9connect.k9connect.models.Dog;
import net.k9connect.k9connect.models.DogDetails;
import net.k9connect.k9connect.models.Photo;
import net.k9connect.k9connect.models.User;
import net.k9connect.k9connect.repositories.DogRepository;
import net.k9connect.k9connect.repositories.UserInfoRepository;
import net.k9connect.k9connect.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DogController {

    private final UserInfoRepository userInfoDao;
    private UserRepository userDao;
    private DogRepository dogDao;

    public DogController(UserInfoRepository userInfoDao, UserRepository userDao, DogRepository dogDao) {
        this.userInfoDao = userInfoDao;
        this.userDao = userDao;
        this.dogDao = dogDao;
    }


    @GetMapping("/dog/create")
    public String createDogForm(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());

        model.addAttribute("dog", new Dog());
        model.addAttribute("photo", new Photo());
        model.addAttribute("details", new DogDetails());
        return "users/create-dog";
    }

    @PostMapping("/dog/create")
    public String submitDog(@RequestParam(name = "has_certs") boolean certs,
            @ModelAttribute Dog dog
    ){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());
        // if cert is "yes"
            //cert = true
        // other wise
            // cert = false


        dogDao.save(dog);
        return "redirect:/dogs";
    }
}
