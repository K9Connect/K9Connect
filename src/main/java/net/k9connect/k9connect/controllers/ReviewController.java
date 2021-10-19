package net.k9connect.k9connect.controllers;

import net.k9connect.k9connect.models.User;
import net.k9connect.k9connect.models.UserReview;
import net.k9connect.k9connect.repositories.UserRepository;
import net.k9connect.k9connect.repositories.UserReviewRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReviewController {
    private UserRepository userDao;
    private UserReviewRepository userReviewDao;

    public ReviewController(UserRepository userDao, UserReviewRepository userReviewDao) {
        this.userDao = userDao;
        this.userReviewDao = userReviewDao;
    }

    @GetMapping("/review/{id}")
    public String getUserReviewForm(@PathVariable long id, Model model) {
        User reviewer = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User reviewed = userDao.getById(id);

        if (reviewer.getId() == reviewed.getId()) {
            return "redirect:/profile";
        }

        UserReview userReview = new UserReview();

        model.addAttribute("reviewedUser", reviewed);
        model.addAttribute("userReview", userReview);

        return "users/review";
    }

    @PostMapping("/review/{id}")
    public String submitUserReview(
            @PathVariable long id,
            @ModelAttribute(name = "userReview") UserReview userReview
    ) {
        User reviewer = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserReview review = new UserReview();

        review.setReviewed(userDao.getById(id));
        review.setReviewer(reviewer);
        review.setStars(userReview.getStars());
        review.setReview(userReview.getReview());

        userReviewDao.save(review);

        return "redirect:/profile/{id}";
    }
}
