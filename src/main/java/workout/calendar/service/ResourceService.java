package workout.calendar.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import workout.calendar.domain.entity.Resources;

public interface ResourceService {

    Page<Resources> getResourceWithPage(Pageable pageable);

}
