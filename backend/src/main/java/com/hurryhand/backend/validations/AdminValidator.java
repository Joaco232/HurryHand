package com.hurryhand.backend.validations;

import com.hurryhand.backend.exceptions.attribute.EmailAlreadyInUseException;
import com.hurryhand.backend.repositories.AdminRepository;
import com.hurryhand.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminValidator {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    public void validateUniqueEmail(String email) throws EmailAlreadyInUseException {

        if (adminRepository.existsAdminByEmail(email) || userRepository.existsUserByEmail(email)) {
            throw new EmailAlreadyInUseException("Ya existe una cuenta registrada con este email.");
        }
    }


}
