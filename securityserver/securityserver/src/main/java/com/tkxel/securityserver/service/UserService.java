package com.tkxel.securityserver.service;

import com.tkxel.securityserver.entity.User;

import javax.security.auth.login.AccountNotFoundException;

public interface UserService {
    public void saveUser(User user);
    public User getUserByNameAndPassword(String name, String password) throws AccountNotFoundException;
}
