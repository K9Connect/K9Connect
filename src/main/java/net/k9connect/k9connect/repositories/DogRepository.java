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

//    Dog findByName(String name);

    List<Dog> findDogsByBreedIsLike(String breed);

//    List<Dog> findDogsByBreedIsLikeAndGenderAndDetails_Has_certs(String breed, char gender, boolean hasCerts);
//    List<Dog> findDogsByDetails_Age (int age);
//    List<Dog> findDogsByDetails_Has_certs (boolean certs);

//
//   @Query(value="select Dog.gender, Dog.breed, DogDetails .has_certs" +
//           where Dog.id=DogDetails.id
//    List<Dog> findByKeyword(@Param("keyword") String keyword);


//    @Query(value="select breed, gender, name, age from Dog ,DogDetails Where Dog.id= DogDetails.id", nativeQuery=true)
//    List<Dog> findByKeyword(@Param("keyword") String keyword);

//    @Query(value = " select Dog d from dog",
//            nativeQuery = true)
//    List<Dog> findDogsByGenderAndBreed(@Param("keyword") String keyword);
}

