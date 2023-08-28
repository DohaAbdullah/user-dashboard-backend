package com.systems.userdashboardbackend.controllers;

import com.systems.userdashboardbackend.dto.AuthResponseDTO;
import com.systems.userdashboardbackend.dto.LoginDto;
import com.systems.userdashboardbackend.dto.RegisterDto;
import com.systems.userdashboardbackend.models.Role;
import com.systems.userdashboardbackend.models.AppUser;
import com.systems.userdashboardbackend.repository.RoleRepository;
import com.systems.userdashboardbackend.repository.AppUserRepository;
import com.systems.userdashboardbackend.security.JwtTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AppUserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenGenerator generateToken;

    @PostMapping("login")
    private ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = generateToken.generateToken(authentication);
            return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
      return null;
    }

    @PostMapping("register")
    private ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("username is taken ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        AppUser user = new AppUser();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        List<Role> useroles = new ArrayList<>();
        registerDto.getRoles().forEach(obj -> {
            Role role = new Role();
            role.setName(obj);
            useroles.add(role);
        });
        user.setRoles(useroles);
        userRepository.save(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.CREATED);
    }
}
