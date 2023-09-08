package workout.calendar.domain.dto.recode;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * workout.calendar.domain.dto.recode.QRecodeTotalDto is a Querydsl Projection type for RecodeTotalDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QRecodeTotalDto extends ConstructorExpression<RecodeTotalDto> {

    private static final long serialVersionUID = -2145477873L;

    public QRecodeTotalDto(com.querydsl.core.types.Expression<String> date, com.querydsl.core.types.Expression<Integer> totalWeight) {
        super(RecodeTotalDto.class, new Class<?>[]{String.class, int.class}, date, totalWeight);
    }

}

