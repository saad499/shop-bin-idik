package org.openlan2.shop_bin_idik.service.impl;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.dto.LoginRequest;
import org.openlan2.shop_bin_idik.dto.LoginResponse;
import org.openlan2.shop_bin_idik.entities.User;
import org.openlan2.shop_bin_idik.exception.AuthenticationException;
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
        if (!isValidCredentials(loginRequest)) {
            throw  new AuthenticationException("Authentification échouée");
        }
        
        return new LoginResponse("Connecté avec succès");
    }

    @Override
    public boolean isValidCredentials(LoginRequest loginRequest) {
        return userRepository.existsByUsernameAndPassword(
                loginRequest.getUsernameOrEmail(),
                PasswordUtil.hashPassword(loginRequest.getPassword())
        );
    }
}
