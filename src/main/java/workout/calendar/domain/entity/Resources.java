package workout.calendar.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import workout.calendar.domain.RoleType;
import workout.calendar.domain.dto.resources.ResourcesDto;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Resources extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resource_id")
    private Long id;

    private String urlName;

    @Column(nullable = false, length = 12)
    @Enumerated(EnumType.STRING)
    private RoleType role;

    public void setResources(ResourcesDto resourcesDto){
        this.urlName = resourcesDto.getUrlName();
        this.role = resourcesDto.getRole();
    }

}
