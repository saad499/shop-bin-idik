package org.openlan2.shop_bin_idik.service.impl;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.dto.LoginRequest;
import org.openlan2.shop_bin_idik.dto.LoginResponse;
import org.openlan2.shop_bin_idik.entities.User;
import org.openlan2.shop_bin_idik.repository.UserRepository;
import org.openlan2.shop_bin_idik.service.AuthService;
import org.openlan2.shop_bin_idik.util.PasswordUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByUsernameOrEmail(
            loginRequest.getUsernameOrEmail(), 
            loginRequest.getUsernameOrEmail()
        );

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            
            // Hash the input password and compare with stored password
            String hashedInputPassword = PasswordUtil.hashPassword(loginRequest.getPassword());
            if (user.getPassword().equals(hashedInputPassword)) {
                return LoginResponse.builder()
                    .message("Connecté")
                    .userId(user.getId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .build();
            } else {
                return LoginResponse.builder()
                    .message("Mot de passe incorrect")
                    .build();
            }
        } else {
            return LoginResponse.builder()
                .message("Vous n'êtes pas inscrit")
                .build();
        }
    }
}
