package net.k9connect.k9connect.repositories;

import net.k9connect.k9connect.models.Dog;
import net.k9connect.k9connect.models.DogReview;
import net.k9connect.k9connect.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.naming.directory.SearchResult;
import javax.transaction.Transactional;
import java.util.List;


public interface DogRepository extends JpaRepository<Dog, Long> {
    Dog findByName (String name);
    List<Dog>  findDogsByBreedIsLike(String breed);
    List<Dog>  findDogsByGender(String gender);
//    List<Dog>  findDogsByGenderAndBreed(String gender, String breed);
}

