package workout.calendar.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import workout.calendar.domain.Date;
import workout.calendar.domain.entity.ScheduleEntity;
import workout.calendar.repository.ScheduleQuerydslRepository;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static workout.calendar.domain.entity.QScheduleEntity.scheduleEntity;

public class ScheduleQuerydslRepositoryImpl implements ScheduleQuerydslRepository {

    private final JPAQueryFactory queryFactory;

    public ScheduleQuerydslRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ScheduleEntity> getList(Date date) {
        return queryFactory
                .select(scheduleEntity)
                .from(scheduleEntity)
                .where(
                        startDateGoe(date.getStartDate()),
                        endDateLoe(date.getEndDate()))
                .orderBy(scheduleEntity.date.desc(), scheduleEntity.num.desc(), scheduleEntity.id.desc())
                .fetch();
    }

    private BooleanExpression startDateGoe(String startDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date date = sdf.parse(startDate);
            return startDate == null ? null : scheduleEntity.date.goe(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private BooleanExpression endDateLoe(String endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date date = sdf.parse(endDate);
            return endDate == null ? null : scheduleEntity.date.loe(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
