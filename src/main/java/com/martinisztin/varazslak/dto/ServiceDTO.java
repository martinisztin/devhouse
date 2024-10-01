package com.martinisztin.varazslak.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter @Getter
public class ServiceDTO {
    private Long id;
    private String name;
    private String description;
    private int price;
    private String form;
    private String ageRange;
    private int duration;
}
