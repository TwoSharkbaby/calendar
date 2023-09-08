package workout.calendar.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import workout.calendar.domain.dto.recode.RecodeMonthListDto;
import workout.calendar.domain.dto.resources.ResourcesDto;
import workout.calendar.domain.entity.Resources;

public interface ResourceService {

    Page<ResourcesDto> getResourcesWithPage(Pageable pageable);

    Long createResource(ResourcesDto resourcesDto);

    ResourcesDto getResource(Long id);

    void deleteResource(Long id);

    Long modifyResource(ResourcesDto resourcesDto);

}
