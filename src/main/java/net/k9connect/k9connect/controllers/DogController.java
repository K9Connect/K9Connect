package net.k9connect.k9connect.controllers;


import net.k9connect.k9connect.models.*;
import net.k9connect.k9connect.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

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
    public String dogIndex(Model model) {
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
    ) {
        UserWithRoles loggedInUser = (UserWithRoles) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());

        System.out.println(dog);
        DogDetails details = dog.getDetails();
        details = dogDetailsDao.save(details);
        dogDao.save(dog);

        return "redirect:/profile";
    }

    @GetMapping("/dog/edit/{id}")
    public String editDog(@PathVariable long id, Model model) {
        UserWithRoles loggedInUser = (UserWithRoles) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());

        model.addAttribute("dog", dogDao.getById(id));

        photoDao.getById(id).getUrl();
        return "users/edit-dog";
    }

    @PostMapping("/dog/edit/{id}")
    public String editDogSend(@ModelAttribute Dog dog, @RequestParam(name = "dog_photos[]") String[] dogphotourls, @ModelAttribute Photo photo, @ModelAttribute DogDetails dogDetails) {

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());
        System.out.println(Arrays.toString(dogphotourls));

        DogDetails details = dog.getDetails();
        details = dogDetailsDao.save(details);
//        photo.setDog(dog);
//        photoDao.save(dog.getPhotos());
        dogDao.save(dog);
//        List<Photo> photos= dog.getPhotos();

//        dog.setPhotos(photos);
//        dog.setDetails(details);
//        details = dogDetailsDao.save(details);


        return "redirect:/profile";
    }

    @PostMapping("/dog/delete/{id}")
    public String deleteDogSend(@ModelAttribute Dog dog, @RequestParam(name = "dog_photos[]") String[] dogphotourls, @ModelAttribute Photo photo, @ModelAttribute DogDetails dogDetails) {

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());
        System.out.println(Arrays.toString(dogphotourls));

        // NEED TO WORK ON ITERATING URLS TO DELETE THEM photos

        dogDetailsDao.delete(dogDetails);
//        photoDao.delete(photo);
        dogDao.delete(dog);



        return "redirect:/profile";
    }
}
