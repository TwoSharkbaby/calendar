package workout.calendar.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import workout.calendar.domain.entity.Resources;
import workout.calendar.repository.ResourcesRepository;
import workout.calendar.service.ResourceService;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourcesRepository resourcesRepository;

    public Page<Resources> getResourceWithPage(Pageable pageable) {
        return resourcesRepository.findAll(pageable);
    }
}
