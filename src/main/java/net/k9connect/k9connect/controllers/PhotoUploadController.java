package net.k9connect.k9connect.controllers;

import net.k9connect.k9connect.models.User;
import net.k9connect.k9connect.models.UserInfo;
import net.k9connect.k9connect.repositories.UserInfoRepository;
import net.k9connect.k9connect.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalTime;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class PhotoUploadController {
    private UserRepository userDao;
    private UserInfoRepository userInfoDao;

    @Value("${FILE_UPLOAD_PATH}")
    private String uploadPath;

    public PhotoUploadController(UserRepository userDao, UserInfoRepository userInfoDao) {
        this.userDao = userDao;
        this.userInfoDao = userInfoDao;
    }

    @GetMapping("/user/photo-upload")
    public String showUserPhotoForm() {
        return "users/photo-upload";
    }

    @PostMapping("/user/photo-upload")
    public String saveUserPhoto(
            @RequestParam(name = "user-photo") MultipartFile uploadedPhoto,
            Model model
    ) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());

        UserInfo userInfo = user.getDetails();

        String timestamp = LocalTime.now().toString().replaceAll("[^0-9]", "");
        String filename = "pfp-" + timestamp + "-" + uploadedPhoto.getOriginalFilename();
        String filepath = Paths.get(uploadPath, filename).toString();

        File destinationFile = new File(filepath);

        try {
            uploadedPhoto.transferTo(destinationFile);
            model.addAttribute("message", "Photo successfully uploaded!");
            userInfo.setPfp("/uploads/" + filename);
            userInfoDao.save(userInfo);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Oops! Something went wrong! " + e);
        }

        return "redirect:/profile/";
    }
}
