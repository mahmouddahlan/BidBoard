package com.mahmoud.bidboard.repository;

import com.mahmoud.bidboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
        
}
