package net.k9connect.k9connect.repositories;

import net.k9connect.k9connect.models.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface DogRepository extends JpaRepository<Dog, Long> {


}
