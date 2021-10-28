package net.k9connect.k9connect.repositories;

import net.k9connect.k9connect.models.UserReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportsRepository extends JpaRepository<UserReport, Long> {
}
