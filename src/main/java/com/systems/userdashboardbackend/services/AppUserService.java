package com.systems.userdashboardbackend.services;

import com.systems.userdashboardbackend.models.AppUser;
import com.systems.userdashboardbackend.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;
    public Set<Optional<AppUser>> findAllUsers() {
        return new HashSet<>((Collection) appUserRepository.findAll());
    }

    public AppUser createUser(AppUser user) {
        return appUserRepository.save(user);
    }

    public AppUser findById(Long id) {
        return appUserRepository.findById(id).orElse(null);
    }

    public void updateUser(AppUser user) {
        appUserRepository.save(user);
    }

    public Boolean deleteUserById(Long id) {
        Optional<AppUser> user = appUserRepository.findById(id);
        if (!user.isEmpty()) {
            appUserRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public AppUser save(AppUser appUser) {
       return appUserRepository.save(appUser);
    }

    public Optional<AppUser> findByUsername(String username) {

        return appUserRepository.findByUsername(username);
    }
}
