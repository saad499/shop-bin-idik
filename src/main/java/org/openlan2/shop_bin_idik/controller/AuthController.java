package org.openlan2.shop_bin_idik.controller;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.dto.LoginRequest;
import org.openlan2.shop_bin_idik.dto.LoginResponse;
import org.openlan2.shop_bin_idik.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
