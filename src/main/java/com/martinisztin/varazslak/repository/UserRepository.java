package com.martinisztin.varazslak.repository;

import com.martinisztin.varazslak.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
