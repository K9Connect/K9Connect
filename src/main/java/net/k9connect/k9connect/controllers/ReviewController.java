package net.k9connect.k9connect.controllers;

import net.k9connect.k9connect.models.Dog;
import net.k9connect.k9connect.models.DogReview;
import net.k9connect.k9connect.models.User;
import net.k9connect.k9connect.models.UserReview;
import net.k9connect.k9connect.repositories.DogRepository;
import net.k9connect.k9connect.repositories.DogReviewRepository;
import net.k9connect.k9connect.repositories.UserRepository;
import net.k9connect.k9connect.repositories.UserReviewRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ReviewController {
    private UserRepository userDao;
    private UserReviewRepository userReviewDao;
    private DogRepository dogDao;
    private DogReviewRepository dogReviewDao;

    public ReviewController(
            UserRepository userDao,
            UserReviewRepository userReviewDao,
            DogRepository dogDao,
            DogReviewRepository dogReviewDao
    ) {
        this.userDao = userDao;
        this.userReviewDao = userReviewDao;
        this.dogDao = dogDao;
        this.dogReviewDao = dogReviewDao;
    }

    @GetMapping("/review/{id}")
    public String getUserReviewForm(@PathVariable long id, Model model) {
        User reviewer = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User reviewed = userDao.getById(id);

        UserReview existingReview = userReviewDao.findByReviewerAndReviewed(reviewer, reviewed);

        if (reviewer.getId() == reviewed.getId() || existingReview != null) {
            return "redirect:/profile/{id}";
        }

        UserReview userReview = new UserReview();

        model.addAttribute("reviewedUser", reviewed);
        model.addAttribute("userReview", userReview);

        return "users/review";
    }

    @PostMapping("/review/{id}")
    public String submitUserReview(
            @PathVariable long id,
            @Valid @ModelAttribute(name = "userReview") UserReview userReview,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "redirect:/review/{id}";
        }

        User reviewer = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserReview review = new UserReview();

        review.setReviewed(userDao.getById(id));
        review.setReviewer(reviewer);
        review.setStars(userReview.getStars());
        review.setReview(userReview.getReview());

        userReviewDao.save(review);

        return "redirect:/profile/{id}";
    }

    @GetMapping("/dogs/review/{id}")
    public String getDogReviewForm(@PathVariable long id, Model model) {
        User reviewer = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Dog reviewedDog = dogDao.getById(id);
        User owner = reviewedDog.getOwner();

        if (reviewer.getId() == owner.getId()) {
            return "redirect:/profile";
        }

        DogReview dogReview = new DogReview();

        model.addAttribute("reviewedDog", reviewedDog);
        model.addAttribute("dogReview", dogReview);

        return "dogs/review";
    }

    @PostMapping("/dogs/review/{id}")
    public String submitDogReview(
            @PathVariable long id,
            @ModelAttribute(name = "dogReview") DogReview dogReview
    ) {
        User reviewer = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Dog reviewedDog = dogDao.getById(id);
        DogReview review = new DogReview();

        review.setDog(reviewedDog);
        review.setCommenter(reviewer);
        review.setStars(dogReview.getStars());
        review.setReview(dogReview.getReview());

        dogReviewDao.save(review);

        return "redirect:/dog/{id}";
    }
}
