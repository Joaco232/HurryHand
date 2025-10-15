package com.hurryhand.backend.services;


import com.hurryhand.backend.dto.servicepost.CreateServicePostDTO;
import com.hurryhand.backend.dto.servicepost.ServicePostForVisualDTO;
import com.hurryhand.backend.enums.SortingDirection;
import com.hurryhand.backend.enums.SortingServicePosts;
import com.hurryhand.backend.exceptions.servicepost.ServicePostNotFoundException;
import com.hurryhand.backend.exceptions.servicepost.ServicePostTitleAlreadyInUseByProviderException;
import com.hurryhand.backend.mappers.ServicePostMapper;
import com.hurryhand.backend.models.Provider;
import com.hurryhand.backend.models.ServicePost;
import com.hurryhand.backend.repositories.ServicePostRepository;
import com.hurryhand.backend.validations.ServicePostValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicePostService {

    private final ServicePostRepository servicePostRepository;
    private final ServicePostValidator servicePostValidator;
    private final ServicePostMapper servicePostMapper;


    @Transactional
    public ServicePost createServicePost(CreateServicePostDTO createServicePostDTO, Provider provider) throws ServicePostTitleAlreadyInUseByProviderException {

        servicePostValidator.validateUniqueTitleByProvider(createServicePostDTO.getTitle(), provider);

        servicePostValidator.validatePastAvailableDates(createServicePostDTO.getAvailableDates());

        return servicePostRepository.save(servicePostMapper.toEntity(createServicePostDTO, provider));
    }


    public ServicePost getServicePostById(Long id) throws ServicePostNotFoundException {

        return servicePostRepository.findServicePostById(id)
                .orElseThrow(() -> new ServicePostNotFoundException("No se encontró un service post con la id."));
    }


    @Transactional
    public Page<ServicePostForVisualDTO> getPostsForVisual(int page, int size, SortingServicePosts sortBy, SortingDirection direction, String query) {

        Sort sort = direction.equals(SortingDirection.DESC)
                ? Sort.by(sortBy.toString()).descending()
                : Sort.by(sortBy.toString()).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        if (query != null && !query.isBlank()) {

            return servicePostRepository.searchFullText(query, pageable)
                    .map(servicePost -> servicePostMapper.toServicePostForVisualDTO(servicePost));

        } else {

            return servicePostRepository.findAll(pageable)
                    .map(servicePost -> servicePostMapper.toServicePostForVisualDTO(servicePost));
        }

    }


    public void uploadPhotosURLsOfServicePost(ServicePost servicePost, List<String> newPhotosURLs) throws ServicePostNotFoundException {

        servicePost.setPhotosURLs(newPhotosURLs);

        servicePostRepository.save(servicePost);

    }







}
