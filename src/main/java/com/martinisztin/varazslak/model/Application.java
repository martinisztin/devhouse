package com.martinisztin.varazslak.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter @Setter

@Entity
@Table(name = "application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column
    private String parentName;

    @Column
    private String parentEmail;

    @Column
    private String parentPhone;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "service_id")
    private Service service;

    @Column
    private String childName;

    @Column
    private int childAge;

    @Column
    private String additionalInfo;

    @Column
    private boolean resolved = false;
}
