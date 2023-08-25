package workout.calendar.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import workout.calendar.domain.RoleType;

@NoArgsConstructor
@Getter
@Setter
public class ResourcesDto {

    private String id;
    private String urlName;
    private RoleType role;
}
