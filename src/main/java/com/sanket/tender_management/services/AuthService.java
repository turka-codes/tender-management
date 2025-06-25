package com.sanket.tender_management.services;

import com.sanket.tender_management.entities.User;
import com.sanket.tender_management.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;

    public User getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userId = (Integer) authentication.getPrincipal();
        return userRepository.findById(userId).orElse(null);
    }
}
