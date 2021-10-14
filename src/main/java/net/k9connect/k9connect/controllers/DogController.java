package net.k9connect.k9connect.controllers;


import net.k9connect.k9connect.models.*;
import net.k9connect.k9connect.repositories.DogDetailsRepository;
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
    private DogDetailsRepository dogDetailsDao;

    public DogController(UserInfoRepository userInfoDao, UserRepository userDao, DogRepository dogDao, DogDetailsRepository dogDetailsDao) {
        this.userInfoDao = userInfoDao;
        this.userDao = userDao;
        this.dogDao = dogDao;
        this.dogDetailsDao = dogDetailsDao;
    }

    @GetMapping("/dogs")
    public String dogIndex(Model model){
        model.addAttribute("dogs", dogDao.findAll());
        return "dogs/index";
    }

    @GetMapping("/dog/create")
    public String createDogForm(Model model) {
        UserWithRoles loggedInUser = (UserWithRoles) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());

        model.addAttribute("dog", new Dog());
        model.addAttribute("photo", new Photo());
        model.addAttribute("details", new DogDetails());
        return "users/create-dog";
    }

    @PostMapping("/dog/create")
    public String submitDog(
            @ModelAttribute Dog dog
    ){
        UserWithRoles loggedInUser = (UserWithRoles) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());

        System.out.println(dog);
        DogDetails details = dog.getDetails();
        details = dogDetailsDao.save(details);
        dogDao.save(dog);

        return "redirect:/profile";
    }
}
