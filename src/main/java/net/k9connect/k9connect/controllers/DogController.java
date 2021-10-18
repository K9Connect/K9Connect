package net.k9connect.k9connect.controllers;


import net.k9connect.k9connect.models.*;
import net.k9connect.k9connect.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class DogController {

    private final UserInfoRepository userInfoDao;
    private UserRepository userDao;
    private DogRepository dogDao;
    private DogDetailsRepository dogDetailsDao;
    private PhotoRepository photoDao;


    public DogController(UserInfoRepository userInfoDao, UserRepository userDao, DogRepository dogDao, DogDetailsRepository dogDetailsDao, PhotoRepository photoDao) {
        this.userInfoDao = userInfoDao;
        this.userDao = userDao;
        this.dogDao = dogDao;
        this.dogDetailsDao = dogDetailsDao;
        this.photoDao = photoDao;
    }

    @GetMapping("/dogs")
    public String dogIndex(Model model){
        model.addAttribute("dogs", dogDao.findAll());
        return "dogs/index";
    }

    @GetMapping("/dog/create")
    public String createDogForm(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());

        model.addAttribute("dog", new Dog());
        model.addAttribute("details", new DogDetails());
        model.addAttribute("photo", new Photo());
        return "users/create-dog";
    }

    @PostMapping("/dog/create")
    public String submitDog(
            @ModelAttribute Dog dog, @ModelAttribute Photo photo
    ){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());

        DogDetails details = dog.getDetails();
        details = dogDetailsDao.save(details);

        dog.setDetails(details);
        dog.setOwner(user);
        dog = dogDao.save(dog);


        photo.setDog(dog);


        photoDao.save(photo);


        return "redirect:/profile";
    }

    @PostMapping("/dog/delete/{id}")
    public String deleteDogSend(@PathVariable long id) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());

        dogDao.deleteById(id);
        return "redirect:/profile";
    }
}
