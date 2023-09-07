package workout.calendar.domain.dto.resources;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import workout.calendar.domain.RoleType;
import workout.calendar.domain.entity.Resources;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
public class ResourcesDto {

    private Long id;

    @NotEmpty(message = "리소스 명은 필수 입니다")
    private String urlName;

    private RoleType role;

    @QueryProjection
    public ResourcesDto(Resources resources) {
        this.id = resources.getId();
        this.urlName = resources.getUrlName();
        this.role = resources.getRole();
    }

}
