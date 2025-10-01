package com.hurryhand.backend.services;

import com.hurryhand.backend.enums.Role;
import com.hurryhand.backend.exceptions.provider.ProviderNotFoundException;
import com.hurryhand.backend.exceptions.provider.UserAlreadyProviderException;
import com.hurryhand.backend.models.Provider;
import com.hurryhand.backend.models.User;
import com.hurryhand.backend.repositories.ProviderRepository;
import com.hurryhand.backend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProviderService {

    private final ProviderRepository providerRepository;
    private final UserRepository userRepository;

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

        List<Role> roles = user.getRoles();
        roles.add(Role.ROLE_PROVIDER);
        user.setRoles(roles);
        userRepository.save(user);

        return providerRepository.save(newProvider);
    }


    public Provider getProviderByUser(User user) throws ProviderNotFoundException {

        return providerRepository.findProviderByUserId(user.getId())
                .orElseThrow(() -> new ProviderNotFoundException("No se encontró el provider."));
    }

    public Provider getProviderByUserEmail(String email) throws ProviderNotFoundException {

        return providerRepository.findProviderByUserEmail(email)
                .orElseThrow(() -> new ProviderNotFoundException("No se encontro el provider."));
    }













}
