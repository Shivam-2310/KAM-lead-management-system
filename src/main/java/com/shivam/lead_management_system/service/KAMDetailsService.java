package com.shivam.lead_management_system.service;

import com.shivam.lead_management_system.entity.KAM;
import com.shivam.lead_management_system.repository.KAMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class KAMDetailsService implements UserDetailsService {

    @Autowired
    @Lazy
    private KAMRepository kamRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        KAM kam = kamRepository.findByUsername(username);
        if (kam == null) {
            throw new UsernameNotFoundException("KAM not found");
        }
        return new User(kam.getUsername(), kam.getPassword(), Collections.emptyList());
    }

    public boolean authenticate(String username, String password) {
        KAM kam = kamRepository.findByUsername(username);

        if (kam != null) {
            return passwordEncoder.matches(password, kam.getPassword());
        }
        return false;
    }

}
