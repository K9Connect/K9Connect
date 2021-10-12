package net.k9connect.k9connect.repositories;

import net.k9connect.k9connect.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository<Message, Long> {
}
