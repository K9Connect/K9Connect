package net.k9connect.k9connect.repositories;

import net.k9connect.k9connect.models.DogDetails;
import net.k9connect.k9connect.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DogDetailsRepository extends JpaRepository<DogDetails, Long> {

    @Transactional
    void deleteByDog_Id(Long dogId);


}
