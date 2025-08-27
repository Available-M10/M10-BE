package com.example.m10.global.security.auth;

import com.example.m10.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        return userRepository.findByAccountId(accountId)
                .map(AuthDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(accountId));
    }
}
