package com.martinisztin.varazslak.repository;

import com.martinisztin.varazslak.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    @Query("SELECT s FROM Service s JOIN s.cities c WHERE c.id = :cityId")
    List<Service> findServicesByCityId(@Param("cityId") Long cityId);
}
