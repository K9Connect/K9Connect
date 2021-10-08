package net.k9connect.k9connect.repositories;

import net.k9connect.k9connect.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
