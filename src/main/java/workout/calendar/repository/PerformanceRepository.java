package workout.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import workout.calendar.domain.entity.Performance;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {
}
