package net.k9connect.k9connect.repositories;

import net.k9connect.k9connect.models.User;
import net.k9connect.k9connect.models.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserReviewRepository extends JpaRepository<UserReview, Long> {
    UserReview findByReviewerAndReviewed(User reviewer, User reviewed);
    List<UserReview> findAllByReviewed(User reviewed);
}
