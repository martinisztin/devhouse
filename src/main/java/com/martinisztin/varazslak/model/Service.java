package com.martinisztin.varazslak.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int price;

    @Column
    private String description;

    @Column
    private String form;

    @Column
    private String ageRange;

    @Column
    private int duration;

    @ManyToMany(mappedBy = "services")
    private Set<City> cities = new HashSet<>();
}
