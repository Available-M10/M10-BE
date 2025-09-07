package com.example.m10.domain.user.facade;

import com.example.m10.domain.user.domain.User;
import com.example.m10.domain.user.domain.repository.UserRepository;
import com.example.m10.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByAccountId(accountId)
                .orElseThrow(UserNotFoundException::new);
    }
}
