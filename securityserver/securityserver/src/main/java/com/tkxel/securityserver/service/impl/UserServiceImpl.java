package com.tkxel.securityserver.service.impl;

import com.tkxel.securityserver.entity.User;
import com.tkxel.securityserver.repository.UserRepository;
import com.tkxel.securityserver.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByNameAndPassword(String name , String password) throws AccountNotFoundException {
        User user = userRepository.findByUsernameAndPassword(name , password);
        if (user == null) {
            throw new AccountNotFoundException("Invalid id and password");
        }
        return user;
    }
}
