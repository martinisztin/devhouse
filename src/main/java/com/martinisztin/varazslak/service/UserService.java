package com.martinisztin.varazslak.service;

import com.martinisztin.varazslak.model.User;
import com.martinisztin.varazslak.repository.RoleRepository;
import com.martinisztin.varazslak.repository.UserRepository;
import com.martinisztin.varazslak.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;


    public String createUser(User user) {
        String genPassword = UserUtils.generatePassword();
        user.setPassword(bCryptPasswordEncoder.encode(genPassword));

        // teacher gets registered (user)
        user.setRole(roleRepository.findById(1L).orElseThrow());

        userRepository.save(user);

        return genPassword;
    }

    public boolean isUsernameTaken(String username) {
        return userRepository.findByUsername(username) != null;
    }

    public User updatePassword(String username, String newPassword) {
        User user = userRepository.findByUsername(username);

        user.setPassword(bCryptPasswordEncoder.encode(newPassword));

        return userRepository.save(user);
    }

    public boolean passwordMatchesWithDb(String username, String userInput) {
        String psw = userRepository.findByUsername(username).getPassword();

        return bCryptPasswordEncoder.matches(userInput, psw);
    }
}
