package com.example.m10.domain.auth.service;

import com.example.m10.domain.auth.dto.request.SignupRequestDto;
import com.example.m10.domain.user.domain.User;
import com.example.m10.domain.user.domain.repository.UserRepository;
import com.example.m10.domain.user.exception.UserExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignupService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupRequestDto dto){
        if (userRepository.existsByAccountId(dto.accountId())) {
            throw new UserExistException(); // 빠른 실패
        }

        try {
            userRepository.save(User.builder()
                    .accountId(dto.accountId())
                    .password(passwordEncoder.encode(dto.password()))
                    .build());
        } catch (DataIntegrityViolationException e) {
            throw new UserExistException();
        }
    }

}
