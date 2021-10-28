package net.k9connect.k9connect.repositories;

import net.k9connect.k9connect.models.Dog;
import net.k9connect.k9connect.models.DogReview;
import net.k9connect.k9connect.models.User;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogReviewRepository extends JpaRepository<DogReview, Long> {
    DogReview findByDogAndCommenter(Dog dog, User commenter);

    List<DogReview> findAllByDog(Dog dog);
}
