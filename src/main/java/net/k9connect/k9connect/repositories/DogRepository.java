package net.k9connect.k9connect.repositories;

import net.k9connect.k9connect.models.Dog;
import net.k9connect.k9connect.models.DogDetails;
import net.k9connect.k9connect.models.DogReview;
import net.k9connect.k9connect.models.Photo;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.naming.directory.SearchResult;
import javax.transaction.Transactional;
import java.util.List;


public interface DogRepository extends JpaRepository<Dog, Long> {


    List<Dog> findDogsByBreedIsLike(String breed);


}

