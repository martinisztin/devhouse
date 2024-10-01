package com.martinisztin.varazslak.repository;

import com.martinisztin.varazslak.model.Service;
import com.martinisztin.varazslak.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
    @Query("SELECT s FROM Worker s JOIN s.workplaces c WHERE c.id = :cityId")
    List<Worker> findWorkersByCityId(@Param("cityId") Long cityId);
}
