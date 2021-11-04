package net.k9connect.k9connect.utils;

import net.k9connect.k9connect.models.DogReview;
import net.k9connect.k9connect.models.UserReview;

import java.util.List;

public class Ratings {
    public static double userAverage(List<UserReview> userReviews) {
        double total = 0;

        for (UserReview review : userReviews) {
            total += review.getStars();
        }

        return total / userReviews.size();
    }

    public static double dogAverage(List<DogReview> dogReviews) {
        double total = 0;

        for (DogReview review : dogReviews) {
            total += review.getStars();
        }

        return total / dogReviews.size();
    }
}
