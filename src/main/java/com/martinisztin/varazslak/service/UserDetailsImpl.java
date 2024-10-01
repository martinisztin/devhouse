package com.martinisztin.varazslak.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class UserDetailsImpl implements UserDetails {
    // no need for setter, because I think the name shouldn't be changed
    private final String username;

    @Setter
    private String password;

    @Setter
    private String firstName;

    @Setter
    private String lastName;

    @Setter
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String username, String password, String firstName, String lastName, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.authorities = authorities;
    }

}
