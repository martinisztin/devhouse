package com.martinisztin.varazslak.model;

import com.martinisztin.varazslak.utils.StringUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor @Getter @Setter

@Entity
@Table(name = "worker")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String profession;

    @Column
    private String description;

    @ManyToMany
    @JoinTable(
            name = "works_at",
            joinColumns = @JoinColumn(name = "worker_id"),
            inverseJoinColumns = @JoinColumn(name = "city_id")
    )
    private Set<City> workplaces = new HashSet<>();

    public String getFullNameHun() {
        return lastName + " " + firstName;
    }

    public String getFullNameSnakeCase() {
        return StringUtils.stripAccents(getFullNameHun().toLowerCase().replace(' ', '_'));
    }
}
