package workout.calendar.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import workout.calendar.domain.dto.resources.ResourcesDto;
import workout.calendar.domain.entity.Resources;
import workout.calendar.repository.ResourcesRepository;
import workout.calendar.security.metadatasource.UrlFilterInvocationSecurityMetadatsSource;
import workout.calendar.service.ResourceService;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourcesRepository resourcesRepository;
    private final UrlFilterInvocationSecurityMetadatsSource urlFilterInvocationSecurityMetadatsSource;

    @Override
    public Page<ResourcesDto> getResourcesWithPage(Pageable pageable) {
        return resourcesRepository.getResourcesWithPage(pageable);
    }

    @Override
    @Transactional
    public Long createResource(ResourcesDto resourcesDto) {
        if (resourcesRepository.findByUrlName(resourcesDto.getUrlName()) != null) {
            return null;
        } else {
            Resources resources = new Resources();
            resources.setResources(resourcesDto);
            Long id = resourcesRepository.save(resources).getId();
            urlFilterInvocationSecurityMetadatsSource.reload();
            return id;
        }
    }

    @Override
    public ResourcesDto getResource(Long id) {
        Optional<Resources> resource = resourcesRepository.findById(id);
        if (resource.isEmpty()){
            return null;
        } else {
            return resource.map(ResourcesDto::new).get();
        }
    }

    @Override
    @Transactional
    public Long modifyResource(ResourcesDto resourcesDto) {
        Optional<Resources> resources = resourcesRepository.findById(resourcesDto.getId());
        if (resources.isPresent()) {
            resources.get().setResources(resourcesDto);
            return resources.get().getId();
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteResource(Long id) {
        resourcesRepository.deleteById(id);
    }



}
