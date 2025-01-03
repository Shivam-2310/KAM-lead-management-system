package com.shivam.lead_management_system.controller;

import com.shivam.lead_management_system.dto.JwtResponse;
import com.shivam.lead_management_system.security.JwtTokenProvider;
import com.shivam.lead_management_system.service.KAMDetailsService;
import com.shivam.lead_management_system.dto.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private KAMDetailsService kamDetailsService;  // Assuming you have a service to load KAM details

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserCredentials userCredentials) {
        String username = userCredentials.getUsername();
        String password = userCredentials.getPassword();

        if (kamDetailsService.authenticate(username, password)) {
            String token = jwtTokenProvider.generateToken(username);
            return ResponseEntity.ok(new JwtResponse(token));
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
