package com.tkxel.securityserver.repository;

import com.tkxel.securityserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsernameAndPassword(String userName , String password);
}
