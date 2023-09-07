package workout.calendar.repository.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import workout.calendar.domain.dto.resources.QResourcesDto;
import workout.calendar.domain.dto.resources.ResourcesDto;
import workout.calendar.domain.entity.QResources;
import workout.calendar.domain.entity.Resources;
import workout.calendar.repository.ResourceRepositoryCustom;

import javax.persistence.EntityManager;

import java.util.List;

import static workout.calendar.domain.entity.QResources.*;

public class ResourceRepositoryCustomImpl implements ResourceRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ResourceRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ResourcesDto> getResourcesWithPage(Pageable pageable) {
        QueryResults<ResourcesDto> results = queryFactory
                .select(new QResourcesDto(resources))
                .from(resources)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<ResourcesDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }
}
