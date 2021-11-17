package net.k9connect.k9connect.controllers;


import net.k9connect.k9connect.models.*;
import net.k9connect.k9connect.repositories.*;
import net.k9connect.k9connect.utils.Ratings;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.Arrays;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DogController {

    private final UserInfoRepository userInfoDao;
    private UserRepository userDao;
    private DogRepository dogDao;
    private DogDetailsRepository dogDetailsDao;
    private PhotoRepository photoDao;
    private DogReviewRepository dogReviewDao;

    public DogController(
            UserInfoRepository userInfoDao,
            UserRepository userDao,
            DogRepository dogDao,
            DogDetailsRepository dogDetailsDao,
            PhotoRepository photoDao,
            DogReviewRepository dogReviewDao
    ) {
        this.userInfoDao = userInfoDao;
        this.userDao = userDao;
        this.dogDao = dogDao;
        this.dogDetailsDao = dogDetailsDao;
        this.photoDao = photoDao;
        this.dogReviewDao = dogReviewDao;
    }

    @GetMapping("/dogs")
    public String dogIndex(Model model) {
        model.addAttribute("dogs", dogDao.findAll());
        return "users/dogs";
    }


    @GetMapping("/dog/create")
    public String createDogForm(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());

        model.addAttribute("dog", new Dog());
        model.addAttribute("details", new DogDetails());
//        model.addAttribute("photo", new Photo());
        return "users/create-dog";
    }

    @PostMapping("/dog/create")
    public String submitDog(
            @ModelAttribute Dog dog//, @ModelAttribute Photo photo
    ) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());

        DogDetails details = dog.getDetails();
        details = dogDetailsDao.save(details);

        dog.setDetails(details);
        dog.setOwner(user);
        dog = dogDao.save(dog);


       // photo.setDog(dog);


        // photoDao.save(photo);


        return "redirect:/dog/" + dog.getId() + "/photo-upload";
    }

    @GetMapping("/dog/edit/{id}")
    public String editDog(@PathVariable long id, Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());
        Dog dog = dogDao.getById(id);
        if (dog.getOwner().getId() == user.getId()) {
        model.addAttribute("dog",dog);
        return "users/edit-dog";
        }
        return "redirect:/profile";
    }

    @PostMapping("/dog/edit/{id}")
    public String editDogSend(
            @ModelAttribute Dog dog,
//            @RequestParam(name = "dog_photos[]") String[] dogphotourls,
            @ModelAttribute Photo photo,
            @ModelAttribute DogDetails dogDetails,
            @PathVariable Long id
//            @RequestParam(name = "dog_photo_ids[]") Long[] dogPhotoIds
    ) {

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());

        // replaced by photo uploads
//        System.out.println(Arrays.toString(dogphotourls));
//        System.out.println(Arrays.toString(dogPhotoIds));
//        List<Photo> photos = new ArrayList<>();
//        for (int i = 0; i < dogphotourls.length; i++) {
//            String url = dogphotourls[i];
//            long dogPhotoId = dogPhotoIds[i];
//            System.out.println(url + " " + dogPhotoId);
//            Photo dogPhoto = photoDao.getById(dogPhotoId);
//            dogPhoto.setUrl(url);
//            photoDao.save(dogPhoto);
//            photos.add(dogPhoto);
//        }

//        dog.setPhotos(photos);

        Dog dogDB = dogDao.getById(id);
        DogDetails details = dog.getDetails();
        details.setId(dogDB.getDetails().getId());
        details = dogDetailsDao.save(details);
        dog.setDetails(details);
        dog.setOwner(user);
        dog.setId(id);
        dogDao.save(dog);

        return "redirect:/profile";
    }


    @PostMapping("/dog/search")
    public String search(@RequestParam String term, @RequestParam String gender, Model model) {
        System.out.println(term);
        System.out.println(gender);
        if (term != null) {
            term = "%" + term + "%";
            List<Dog> listOfDogs = dogDao.findDogsByBreedIsLike(term);
            model.addAttribute("dogs",listOfDogs);
        }
        Boolean certs = false;

            if (gender.charAt(0) != 'a') {
                if (gender.charAt(0) == 'M') {
                    List<Dog> listOfDogs = dogDao.findDogsByBreedIsLike(term);
                    List<Dog> dogsList = new ArrayList<>();
                    for (int i = 0; i < listOfDogs.size(); i++) {
                        if (listOfDogs.get(i).getGender() == 'M') {
                            dogsList.add(listOfDogs.get(i));
                        } else if (certs) {
                            if (listOfDogs.get(i).getDetails().isHas_certs()) {
                                dogsList.add(listOfDogs.get(i));
                            }
                        }
                    }
                    model.addAttribute("dogs", dogsList);
                }
                if (gender.charAt(0) == 'F') {
                    List<Dog> listOfDogs = dogDao.findDogsByBreedIsLike(term);
                    List<Dog> dogsList = new ArrayList<>();
                    for (int i = 0; i < listOfDogs.size(); i++) {
                        if (listOfDogs.get(i).getGender() == 'F') {
                            dogsList.add(listOfDogs.get(i));
                        } else if (certs) {
                            if (listOfDogs.get(i).getDetails().isHas_certs()) {
                                dogsList.add(listOfDogs.get(i));
                            }
                        }
                    }
                    model.addAttribute("dogs", dogsList);
                }
            } else if (gender.charAt(0) == 'a'){
                List<Dog> listOfDogs = dogDao.findDogsByBreedIsLike(term);
                model.addAttribute("dogs", listOfDogs);
            } else if (certs) {
                List<Dog> allDogs = dogDao.findAll();
                List<Dog> dogsWithCerts = new ArrayList<>();
                for (int i = 0; i < allDogs.size(); i++) {
                    if (allDogs.get(i).getDetails().isHas_certs()) {
                       dogsWithCerts.add(allDogs.get(i));
                    }
                }
                model.addAttribute("dogs", dogsWithCerts);
            }
         else {
            model.addAttribute("dogs", dogDao.findAll());
        }

        return "users/dogs";
    }

    @PostMapping("/dog/delete/{id}")
    public String deleteDogSend(@PathVariable long id) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());

        Dog currentDog = dogDao.getById(id);
        DogDetails detailsToDelete = currentDog.getDetails();


        dogDao.deleteById(id);
        dogDetailsDao.delete(detailsToDelete);
        photoDao.deleteAllByDog_Id(id);

        return "redirect:/profile";
    }



    @PostMapping("/dog/photo/{photoId}/{dogId}")
    public String deleteDogPhoto(@PathVariable long photoId, @PathVariable long dogId) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());
        // TODO: Only deletes url from the database, find out how to delete file from directory

        photoDao.deleteById(photoId);
        return "redirect:/dog/" + dogId;
    }

    @GetMapping("/photo/add/{id}")
    public String addPhotoForm(@PathVariable Long id, Model model) {
//        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = userDao.findByUsername(loggedInUser.getUsername());

        model.addAttribute("dogId", id);
        model.addAttribute("photo", new Photo());

        return "users/add-photo";
    }

    @PostMapping("/photo/add/{id}")
    public String addPhotoForm(@PathVariable Long id, @ModelAttribute Photo photo) {
//        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = userDao.findByUsername(loggedInUser.getUsername());
        photo.setId(0);
        photo.setDog(dogDao.getById(id));
        photoDao.save(photo);

        return "redirect:/dogs/profile";
    }

    @GetMapping("/dog/{id}")
    public String showDogProfile(@PathVariable long id, Model model) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDao.getById(principal.getId());

        Dog dogInDb = dogDao.getById(id);

        DogReview currentDogReview = dogReviewDao.findByDogAndCommenter(dogInDb, currentUser);

        boolean userHasNotReviewedDog = currentDogReview == null;

        List<DogReview> dogReviews = dogReviewDao.findAllByDog(dogInDb);
        double dogAverage = dogReviews.isEmpty() ? 0 : Ratings.dogAverage(dogReviews);

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("dog", dogInDb);
        model.addAttribute("userHasNotReviewedDog", userHasNotReviewedDog);
        model.addAttribute("dogAverage", dogAverage);


        return "dogs/profile";

    }

}


