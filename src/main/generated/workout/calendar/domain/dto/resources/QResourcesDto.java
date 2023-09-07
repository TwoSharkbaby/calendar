package workout.calendar.domain.dto.resources;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * workout.calendar.domain.dto.resources.QResourcesDto is a Querydsl Projection type for ResourcesDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QResourcesDto extends ConstructorExpression<ResourcesDto> {

    private static final long serialVersionUID = 959848541L;

    public QResourcesDto(com.querydsl.core.types.Expression<? extends workout.calendar.domain.entity.Resources> resources) {
        super(ResourcesDto.class, new Class<?>[]{workout.calendar.domain.entity.Resources.class}, resources);
    }

}

