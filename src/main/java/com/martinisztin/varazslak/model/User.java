package com.martinisztin.varazslak.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    // apparently username is actually email - I took the task description too literally
    @Column
    private String username;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String password;

    @Column
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;
}
