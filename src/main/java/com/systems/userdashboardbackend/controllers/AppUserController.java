package com.systems.userdashboardbackend.controllers;

import com.systems.userdashboardbackend.models.AppUser;
import com.systems.userdashboardbackend.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@RestController
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/app/users")
    public Set<Optional<AppUser>> getAllUsers(@RequestParam(required = false) String username) {
        if (username == null) {
            return appUserService.findAllUsers();
        } else {
            HashSet<Optional<AppUser>> users = new HashSet<>();
            users.add(appUserService.findByUsername(username));
            return users;
        }
    }

    @GetMapping("/app/{id}")
    public ResponseEntity<Optional<AppUser>> findById(@PathVariable Long id) {
        Optional<AppUser> appUser = Optional.ofNullable(appUserService.findById(id));
        if (appUser != null) {
            return ResponseEntity.ok(appUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/app/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = appUserService.deleteUserById(id);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/app/create-user")
    public ResponseEntity<AppUser> addUser(@RequestBody AppUser newUser) {
        AppUser createdUser = appUserService.createUser(newUser);
        if (createdUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/app/user/{id}")
    public ResponseEntity<AppUser> updateUser(@PathVariable Long id, @RequestBody AppUser updatedUser) {
        Optional<AppUser> optionalUser = Optional.ofNullable(appUserService.findById(id));
        if (optionalUser.isPresent()) {
            AppUser appUser = optionalUser.get();

            if (updatedUser.getUsername() != null) {
                appUser.setUsername(updatedUser.getUsername());
            }
            if (updatedUser.getPassword() != null) {
                appUser.setPassword(updatedUser.getPassword());
            }

            AppUser updated = appUserService.save(appUser);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updated);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/app/logged-in-user")//logged-in-user
    public String getLoggedInUser(Principal principal) {
        return principal.getName();
    }
}
