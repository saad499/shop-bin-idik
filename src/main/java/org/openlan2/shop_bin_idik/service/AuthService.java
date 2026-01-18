package org.openlan2.shop_bin_idik.service;

import org.openlan2.shop_bin_idik.dto.LoginRequest;
import org.openlan2.shop_bin_idik.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
}
