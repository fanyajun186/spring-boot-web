package com.neo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neo.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    User findByUserNameOrEmail(String username, String email);
    
}