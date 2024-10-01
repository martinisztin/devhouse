package com.martinisztin.varazslak.service;

import com.martinisztin.varazslak.model.City;
import com.martinisztin.varazslak.model.Worker;
import com.martinisztin.varazslak.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    public List<Worker> getAllWorker() {
        return workerRepository.findAll();
    }

    public void addCityToWorker(Long workerId, City city) {
        Worker worker = workerRepository.findById(workerId).orElseThrow(() -> new IllegalArgumentException("No city found"));
        worker.getWorkplaces().add(city);
        workerRepository.save(worker);
    }

}
