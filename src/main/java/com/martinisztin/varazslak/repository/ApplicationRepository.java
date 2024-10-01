package com.martinisztin.varazslak.repository;

import com.martinisztin.varazslak.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
