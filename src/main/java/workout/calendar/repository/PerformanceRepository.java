package workout.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import workout.calendar.domain.entity.Performance;

import java.util.List;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    List<Performance> findByRecodeId(Long id);

}
