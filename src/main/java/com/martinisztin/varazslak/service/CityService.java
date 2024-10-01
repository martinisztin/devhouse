package com.martinisztin.varazslak.service;


import com.martinisztin.varazslak.model.City;
import com.martinisztin.varazslak.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City saveCity(City city) {
        return cityRepository.save(city);
    }

    public City getCityByName(String name) {
        for (City c : cityRepository.findAll()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    public void addServiceToCity(Long cityId, com.martinisztin.varazslak.model.Service service) {
        City city = cityRepository.findById(cityId).orElseThrow(() -> new IllegalArgumentException("No city found"));
        city.getServices().add(service);
        cityRepository.save(city);
    }
}
