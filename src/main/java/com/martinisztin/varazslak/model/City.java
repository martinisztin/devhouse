package com.martinisztin.varazslak.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter @Setter

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column
    public String name;

    @ManyToMany
    @JoinTable(
            name = "has_service",
            joinColumns = @JoinColumn(name = "city_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private Set<Service> services = new HashSet<>();

    @ManyToMany(mappedBy = "workplaces")
    private Set<Worker> workers = new HashSet<>();

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private Set<Application> application;
}
