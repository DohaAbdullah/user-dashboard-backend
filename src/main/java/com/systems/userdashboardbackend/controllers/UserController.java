package com.systems.userdashboardbackend.controllers;


import com.systems.userdashboardbackend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.systems.userdashboardbackend.services.UserService;

import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-users")
    public Set<User> getAllUsers(@RequestParam(required = false) String email) {
        if (email == null) {
            return userService.findAllUsers();
        } else {
            HashSet<User> users = new HashSet<>();
            users.add(userService.findUserByEmail(email));
            return users;
        }
    }

    @GetMapping("/hello")
    public String getHello() {
       return "hello Doha";
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUserById(id);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody User newUser) {
        User createdUser = userService.createUser(newUser);
        if (createdUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

//            if (updatedUser.getUsername() != null) {
//                user.setUsername(updatedUser.getUsername());
//            }
//            if (updatedUser.getPassword() != null) {
//                user.setPassword(updatedUser.getPassword());
//            }

            User updated = userService.save(user);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updated);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/current-user")//logged-in-user
    public String getLoggedInUser(Principal principal) {
        return principal.getName();
    }

}
