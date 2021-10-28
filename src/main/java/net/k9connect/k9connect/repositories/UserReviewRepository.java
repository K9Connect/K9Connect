package net.k9connect.k9connect.repositories;

import net.k9connect.k9connect.models.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReviewRepository extends JpaRepository<UserReview, Long> {
}
