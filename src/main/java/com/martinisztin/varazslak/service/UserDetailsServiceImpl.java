package com.martinisztin.varazslak.service;

import com.martinisztin.varazslak.model.User;
import com.martinisztin.varazslak.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return new UserDetailsImpl(
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                Collections.singleton(new SimpleGrantedAuthority(user.getRole().getRoleName()))
        );
    }
}
