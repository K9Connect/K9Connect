package net.k9connect.k9connect.repositories;

import net.k9connect.k9connect.models.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Long> {
    Dog findByName (String name);
}
