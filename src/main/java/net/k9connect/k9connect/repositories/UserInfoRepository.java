package net.k9connect.k9connect.repositories;

import net.k9connect.k9connect.models.User;
import net.k9connect.k9connect.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
