package workout.calendar.repository;

import workout.calendar.domain.Date;
import workout.calendar.domain.entity.ScheduleEntity;

import java.util.List;

public interface ScheduleQuerydslRepository {

    List<ScheduleEntity> getList(Date date);

}
