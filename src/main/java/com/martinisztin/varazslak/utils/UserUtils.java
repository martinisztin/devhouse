package com.martinisztin.varazslak.utils;

import com.martinisztin.varazslak.service.UserDetailsImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.SecureRandom;
import java.util.stream.Collectors;

public class UserUtils {
    private UserUtils() {}

    public static UserDetailsImpl getUserFromAuth() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetailsImpl user = null;
        if (principal instanceof UserDetailsImpl) {
            user = ((UserDetailsImpl)principal);
        }

        return user;
    }

    public static String getRole(UserDetails user) {
        String role = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList()
                .get(0);

        return role.split("_")[1];
    }

    public static String generatePassword() {
        return new SecureRandom().ints(10, 'A', '[')
                .mapToObj(ch -> String.valueOf((char)ch)).collect(Collectors.joining());
    }
}
