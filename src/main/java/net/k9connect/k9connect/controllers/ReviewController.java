package net.k9connect.k9connect.controllers;

import net.k9connect.k9connect.models.User;
import net.k9connect.k9connect.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ReviewController {
    private UserRepository userDao;

    public ReviewController(UserRepository userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/review/{id}")
    public String getUserReviewForm(@PathVariable long id, Model model) {
        User reviewer = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("reviewedUser", userDao.getById(id));
        model.addAttribute("reviewingUser", reviewer);

        return "users/review";
    }
}
