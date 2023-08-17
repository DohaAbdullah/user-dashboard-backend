package com.systems.userdashboardbackend.services;

import com.systems.userdashboardbackend.models.AppUser;
import com.systems.userdashboardbackend.models.User;
import com.systems.userdashboardbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public Set<User> findAllUsers() {
        return new HashSet<>((Collection) userRepository.findAll());
    }
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userRepository.findById(id).orElse(null));
    }
    public User save(User user) {
        return userRepository.save(user);
    }

    public Boolean deleteUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isEmpty()) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
