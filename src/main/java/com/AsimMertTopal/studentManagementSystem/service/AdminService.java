package com.AsimMertTopal.studentManagementSystem.service;

import com.AsimMertTopal.studentManagementSystem.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String userName) {
                return adminRepository.findByEmail(userName)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }
}
