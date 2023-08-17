package com.systems.userdashboardbackend.repository;

import com.systems.userdashboardbackend.models.AppUser;
import com.systems.userdashboardbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;


public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
    Set<User> findAllByCityLike(String city);
}