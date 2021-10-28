package net.k9connect.k9connect.repositories;

import net.k9connect.k9connect.models.DogReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogReviewRepository extends JpaRepository<DogReview, Long> {
}
