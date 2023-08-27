package workout.calendar.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import workout.calendar.domain.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    Page<User> findRoleByUsernameContains(String info, Pageable pageable);

    Page<User> findRoleByNicknameContains(String info, Pageable pageable);
}
