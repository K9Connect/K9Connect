package net.k9connect.k9connect.controllers;

import net.k9connect.k9connect.models.*;
import net.k9connect.k9connect.repositories.*;
import net.k9connect.k9connect.utils.Ratings;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserProfileController {

    private final UserInfoRepository userInfoDao;
    private UserRepository userDao;
    private DogRepository dogDao;
    private DogDetailsRepository dogDetailsDao;
    private PhotoRepository photoDao;
    private UserReviewRepository userReviewDao;
    private PasswordEncoder passwordEncoder;


    public UserProfileController(
            UserInfoRepository userInfoDao,
            UserRepository userDao,
            DogRepository dogDao,
            DogDetailsRepository dogDetailsDao,
            PhotoRepository photoDao,
            UserReviewRepository userReviewDao,
            PasswordEncoder passwordEncoder
    ) {
        this.userInfoDao = userInfoDao;
        this.userDao = userDao;
        this.dogDao = dogDao;
        this.dogDetailsDao = dogDetailsDao;
        this.photoDao = photoDao;
        this.userReviewDao = userReviewDao;
        this.passwordEncoder = passwordEncoder;

    }

    @GetMapping("/profile/create")
    public String createProfile(Model model) {

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());
        if (user.getDetails() != null) {
            return "redirect:/profile";
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUser(user);


        model.addAttribute("userprofile", userInfo);

        return "users/create-profile";

    }

    @PostMapping("/profile/create")
    public String createProfileSend(@ModelAttribute UserInfo userInfo) {

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());
        user.setDetails(userInfo);

        userInfoDao.save(userInfo);
        return "redirect:/profile";
    }

    @GetMapping("/profile")
    public String redirectProfile(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());

        if (user.getDetails() == null) {
            return "redirect:/profile/create";
        }
        return "redirect:/profile/" + loggedInUser.getId();

    }

    @GetMapping("/profile/{id}")
    public String viewProfile(@PathVariable long id, Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());

        model.addAttribute("loggedinuser", user);

        User displayedUser = userDao.getById(id);
        UserInfo displayedInfo = displayedUser.getDetails();

        model.addAttribute("user", displayedUser);
        model.addAttribute("info", displayedInfo);

        model.addAttribute("dogs", displayedUser.getDogs()); // should be user dogs
        model.addAttribute("photo", displayedUser.getDetails().getPfp());

        List<UserReview> userReviews = userReviewDao.findAllByReviewed(displayedUser);
        double averageStars = userReviews.isEmpty() ? 0 : Ratings.userAverage(userReviews);

        model.addAttribute("averageStars", averageStars);

        return "users/profile";

    }

    @GetMapping("/profile/edit")
    public String editProfile(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());

        model.addAttribute("user", user.getDetails());

        return "users/edit-profile";
    }

    @PostMapping("/profile/edit")
    public String editProfileSend(@ModelAttribute UserInfo userInfo) {

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());

        userInfo.setId(user.getDetails().getId());

        userInfoDao.save(userInfo);
        return "redirect:/profile/";
    }


    @GetMapping("/profile/manage")
    public String manageAccountInfoView(Model model){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());

//        System.out.println(user);

        model.addAttribute("user",user);

        return "users/manage";
    }


    @PostMapping("/profile/deactivate")
    public String deactivateProfile(){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());

        user.setStatus(Statuses.inactive);
        userDao.save(user);

        return "redirect:/login";
    }

    @PostMapping("/profile/manage")
    public String manageAccountInfo (@ModelAttribute User userData) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());
        String hash = passwordEncoder.encode(userData.getPassword());
        user.setPassword(hash);
        user.setUsername(userData.getUsername());
        user.setEmail(userData.getEmail());
        userDao.save(user);

        return "redirect:/login";
    }
}




