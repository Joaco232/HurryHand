package com.hurryhand.backend.repositories;

import com.hurryhand.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserById(Long id);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByPhoneNumber(String phoneNumber);

    boolean existsUserByEmail(String email);
    boolean existsUserById(Long id);
    boolean existsUserByPhoneNumber(String phoneNumber);







}
