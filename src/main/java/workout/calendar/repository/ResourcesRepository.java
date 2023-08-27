package workout.calendar.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import workout.calendar.domain.entity.Resources;

public interface ResourcesRepository extends JpaRepository<Resources, Long> {
    Page<Resources> findAll(Pageable pageable);
}
