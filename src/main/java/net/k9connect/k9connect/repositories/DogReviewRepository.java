package net.k9connect.k9connect.repositories;

import net.k9connect.k9connect.models.Dog;
import net.k9connect.k9connect.models.DogReview;
import net.k9connect.k9connect.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogReviewRepository extends JpaRepository<DogReview, Long> {
    DogReview findByDogAndCommenter(Dog dog, User commenter);
}
