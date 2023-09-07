package workout.calendar.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import workout.calendar.domain.dto.resources.ResourcesDto;

public interface ResourceRepositoryCustom {

    Page<ResourcesDto> getResourcesWithPage(Pageable pageable);
}
