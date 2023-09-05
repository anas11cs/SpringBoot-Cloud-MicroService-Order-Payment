package com.tkxel.securityserver.service;

import com.tkxel.securityserver.entity.User;

import java.util.Map;

public interface JwtGenerator {
    Map<String, String> generateToken(User user);
}
