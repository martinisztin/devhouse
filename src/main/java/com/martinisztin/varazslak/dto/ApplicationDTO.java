package com.martinisztin.varazslak.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class ApplicationDTO {
    private String city;
    private String parentName;
    private String parentEmail;
    private String parentPhone;
    private Long service;
    private String childName;
    private Integer childAge;
    private String additionalInfo;
}
