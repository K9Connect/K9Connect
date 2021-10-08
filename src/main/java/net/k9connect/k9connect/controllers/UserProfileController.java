package net.k9connect.k9connect.controllers;

import net.k9connect.k9connect.models.User;
import net.k9connect.k9connect.models.UserInfo;
import net.k9connect.k9connect.repositories.UserInfoRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserProfileController {

    private final UserInfoRepository userInfoDao;

    UserProfileController(UserInfoRepository userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    @GetMapping("/profile/create")
    public String createProfile(Model model) {

        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserInfo userInfo = new UserInfo();
        userInfo.setUser(loggedInUser);
        model.addAttribute("userprofile", userInfo);

        return "users/create-profile";

    }

    @PostMapping("/profile/create")
    public String createProfileSend(@ModelAttribute UserInfo userInfo){

        userInfoDao.save(userInfo);
        return "redirect:/user/profile";
    }



}


