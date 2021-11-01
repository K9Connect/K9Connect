package net.k9connect.k9connect.repositories;

import net.k9connect.k9connect.models.Dog;
import net.k9connect.k9connect.models.DogDetails;
import net.k9connect.k9connect.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DogDetailsRepository extends JpaRepository<DogDetails, Long> {

    @Transactional
    void deleteByDog_Id(Long dogId);

//    @Query(value="select * from DogDetails where DogDetails.age like %:keyword%")
//    List<DogDetails> findByKeyword(@Param("keyword") String keyword);
}
