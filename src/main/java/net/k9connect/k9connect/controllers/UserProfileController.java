package net.k9connect.k9connect.controllers;

import net.k9connect.k9connect.models.*;
import net.k9connect.k9connect.repositories.UserInfoRepository;
import net.k9connect.k9connect.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserProfileController {

    private final UserInfoRepository userInfoDao;
    private UserRepository userDao;

    public UserProfileController(UserInfoRepository userInfoDao, UserRepository userDao) {
        this.userInfoDao = userInfoDao;
        this.userDao = userDao;
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
    public String createProfileSend(@ModelAttribute UserInfo userInfo){

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


        User displayedUser = userDao.getById(id);
        UserInfo displayedInfo = userInfoDao.getById(id);

        model.addAttribute("user", displayedUser);
        model.addAttribute("info", displayedInfo);
//        model.addAttribute("dogs", dogDao.findAll());

        return "users/profile";

    }



}


