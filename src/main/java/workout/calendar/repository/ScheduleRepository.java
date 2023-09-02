package workout.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import workout.calendar.domain.entity.ScheduleEntity;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long>, ScheduleQuerydslRepository {


}
