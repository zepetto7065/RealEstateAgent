package me.sangmoon.RealEstateAgent.service;

import lombok.RequiredArgsConstructor;
import me.sangmoon.RealEstateAgent.domain.Authority;
import me.sangmoon.RealEstateAgent.domain.User;
import me.sangmoon.RealEstateAgent.dto.UserDto;
import me.sangmoon.RealEstateAgent.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User signup(UserDto userDto) {
        if (userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();
        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .activated(true)
                .authorities(Collections.singleton(authority))
                .build();

        return userRepository.save(user);
    }
}
