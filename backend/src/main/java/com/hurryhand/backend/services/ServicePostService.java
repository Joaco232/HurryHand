package com.hurryhand.backend.services;


import com.hurryhand.backend.dto.servicepost.CreateServicePostDTO;
import com.hurryhand.backend.exceptions.servicepost.ServicePostTitleAlreadyInUseByProviderException;
import com.hurryhand.backend.mappers.ServicePostMapper;
import com.hurryhand.backend.models.Provider;
import com.hurryhand.backend.models.ServicePost;
import com.hurryhand.backend.repositories.ServicePostRepository;
import com.hurryhand.backend.validations.ServicePostValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServicePostService {

    private final ServicePostRepository servicePostRepository;
    private final ServicePostValidator servicePostValidator;
    private final ServicePostMapper servicePostMapper;


    public ServicePost createServicePost(CreateServicePostDTO createServicePostDTO, Provider provider) throws ServicePostTitleAlreadyInUseByProviderException {

        servicePostValidator.validateUniqueTitleByProvider(createServicePostDTO.getTitle(), provider);

        return servicePostRepository.save(servicePostMapper.toEntity(createServicePostDTO, provider));
    }












}
