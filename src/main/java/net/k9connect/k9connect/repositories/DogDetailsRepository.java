package net.k9connect.k9connect.repositories;

import net.k9connect.k9connect.models.DogDetails;
import net.k9connect.k9connect.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface DogDetailsRepository extends JpaRepository<DogDetails, Long> {
    @Transactional
    void deleteByDog_Id(Long dogId);
}
