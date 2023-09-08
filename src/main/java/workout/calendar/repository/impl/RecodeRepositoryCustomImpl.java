package workout.calendar.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import workout.calendar.domain.dto.recode.QRecodeTotalDto;
import workout.calendar.domain.dto.recode.RecodeTotalDto;
import workout.calendar.repository.RecodeRepositoryCustom;

import javax.persistence.EntityManager;
import java.util.List;

import static workout.calendar.domain.entity.QRecode.recode;

public class RecodeRepositoryCustomImpl implements RecodeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public RecodeRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<RecodeTotalDto> getTotalWeight(Long id, String startDateStr, String endDateStr) {
        return queryFactory
                .select(new QRecodeTotalDto(
                        recode.date,
                        recode.totalWeight))
                .from(recode)
                .where(recode.user.id.eq(id)
                        .and(recode.date.between(startDateStr, endDateStr)))
                .orderBy(recode.date.asc())
                .fetch();
    }


}
