package net.k9connect.k9connect.repositories;

import net.k9connect.k9connect.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

}
