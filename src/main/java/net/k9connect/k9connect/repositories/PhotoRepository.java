package net.k9connect.k9connect.repositories;

import net.k9connect.k9connect.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    @Transactional
    void deleteAllByDog_Id(Long dogId);
}
