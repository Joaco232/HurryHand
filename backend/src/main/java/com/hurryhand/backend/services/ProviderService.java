package com.hurryhand.backend.services;

import com.hurryhand.backend.enums.Role;
import com.hurryhand.backend.exceptions.Provider.UserAlreadyProviderException;
import com.hurryhand.backend.models.Provider;
import com.hurryhand.backend.models.User;
import com.hurryhand.backend.repositories.ProviderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProviderService {

    private final ProviderRepository providerRepository;

    @Transactional
    public Provider setUserAsProvider(User user) throws UserAlreadyProviderException {

        if (user.getRoles().contains(Role.ROLE_PROVIDER)) {

            throw new UserAlreadyProviderException("El usuario ya esta registrado como provider.");
        }

        Optional<Provider> optionalProvider = providerRepository.findProviderByUserId(user.getId());

        if (optionalProvider.isPresent()) {

            throw new UserAlreadyProviderException("El usuario ya esta registrado como provider.");
        }

        Provider newProvider = Provider.builder().user(user).build();

        return providerRepository.save(newProvider);
    }













}
