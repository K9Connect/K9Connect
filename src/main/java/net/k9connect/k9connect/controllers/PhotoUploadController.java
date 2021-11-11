package net.k9connect.k9connect.controllers;

import net.k9connect.k9connect.models.Dog;
import net.k9connect.k9connect.models.Photo;
import net.k9connect.k9connect.models.User;
import net.k9connect.k9connect.models.UserInfo;
import net.k9connect.k9connect.repositories.DogRepository;
import net.k9connect.k9connect.repositories.PhotoRepository;
import net.k9connect.k9connect.repositories.UserInfoRepository;
import net.k9connect.k9connect.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalTime;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class PhotoUploadController {
    private UserRepository userDao;
    private UserInfoRepository userInfoDao;
    private PhotoRepository photoDao;
    private DogRepository dogDao;

    @Value("${FILE_UPLOAD_PATH}")
    private String uploadPath;

    public PhotoUploadController(UserRepository userDao, UserInfoRepository userInfoDao, PhotoRepository photoDao, DogRepository dogDao) {
        this.userDao = userDao;
        this.userInfoDao = userInfoDao;
        this.photoDao = photoDao;
        this.dogDao = dogDao;
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

    @GetMapping("/dog/{id}/photo-upload")
    public String showDogPhotoForm(@PathVariable long id, Model model) {
        model.addAttribute("dog", dogDao.getById(id));
        return "dogs/photo-upload";
    }

    @PostMapping("/dog/{id}/photo-upload")
    public String saveDogPhoto(
            @RequestParam(name = "dog-photo") MultipartFile uploadedPhoto,
            @PathVariable long id,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
//        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = userDao.findByUsername(loggedInUser.getUsername());

        Dog dog = dogDao.getById(id);

        String timestamp = LocalTime.now().toString().replaceAll("[^0-9]", "");
        String filename = "dog-" + timestamp + "-" + uploadedPhoto.getOriginalFilename();
        String filepath = Paths.get(uploadPath, filename).toString();

        File destinationFile = new File(filepath);

        Photo photo = new Photo();

        try {
            uploadedPhoto.transferTo(destinationFile);

            redirectAttributes.addFlashAttribute("message", "Photo successfully uploaded!");

            photo.setDog(dog);
            photo.setUrl("/uploads/" + filename);

            photoDao.save(photo);

        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Oops! Something went wrong! " + e);
        }

        return "redirect:/dog/{id}/photo-upload";
    }
}
