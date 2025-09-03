package com.hurryhand.backend.validations;


import com.hurryhand.backend.exceptions.attribute.EmailAlreadyInUseException;
import com.hurryhand.backend.exceptions.user.UnderAgeUserException;
import com.hurryhand.backend.repositories.UserRepository;
import com.hurryhand.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;
    private static final int MINIMUM_AGE = 18;

    public void validateUniqueEmail(String email) throws EmailAlreadyInUseException {

        if (userRepository.existsUserByEmail(email)) {
            throw new EmailAlreadyInUseException("Ya existe una cuenta registrada con este email.");
        }
    }

    public void validateUserAge(LocalDate birthdate) {

        int age = Period.between(birthdate, LocalDate.now()).getYears();
        if (age < MINIMUM_AGE) {
            throw new UnderAgeUserException("El usuario debe ser mayor de edad para registrarse (+18).");
        }
    }





}
