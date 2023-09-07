package workout.calendar.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import workout.calendar.domain.entity.Resources;

import java.util.List;

public interface ResourcesRepository extends JpaRepository<Resources, Long>, ResourceRepositoryCustom {

    Resources findByUrlName(String urlName);
}
