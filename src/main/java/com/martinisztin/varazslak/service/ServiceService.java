package com.martinisztin.varazslak.service;

import com.martinisztin.varazslak.model.City;
import com.martinisztin.varazslak.repository.CityRepository;
import com.martinisztin.varazslak.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private CityService cityService;
    public List<com.martinisztin.varazslak.model.Service> getDistinctServiceObjects() {
        List<com.martinisztin.varazslak.model.Service> allServices = serviceRepository.findAll();

        return new ArrayList<>(allServices.stream()
                .collect(Collectors.toMap(
                        service -> service.getName().replaceAll("\\s*\\(.*\\)", ""), // Key: name without bracket
                        Function.identity(), // Value: entire Service object
                        (existing, replacement) -> existing, // Keep the first occurrence
                        LinkedHashMap::new // Use LinkedHashMap to preserve the order
                ))
                .values());
    }

    public List<com.martinisztin.varazslak.model.Service> getServicesForCity(String cityName) {
        List<City> cities = cityService.getAllCities();

        for(City city : cities) {
            if(city.getName().equals(cityName)) {
                return serviceRepository.findServicesByCityId(city.getId());
            }
        }

        return null;
    }

    public List<com.martinisztin.varazslak.model.Service> getDistinctServicesForCity(String accentedCity) {
        City currentCity = cityService.getCityByName(accentedCity);

        List<com.martinisztin.varazslak.model.Service> services = new ArrayList<>();

        for(com.martinisztin.varazslak.model.Service service : getDistinctServiceObjects()) {

            if(!currentCity.getServices().contains(service)) {
                continue;
            }

            String[] name = service.getName().split("\\(");
            if(name.length == 2) {
                service.setName(name[0].strip());
            }

            services.add(service);
        }

        return services;
    }


    public com.martinisztin.varazslak.model.Service getServiceById(Long id) {
        return serviceRepository.findById(id).orElseThrow();
    }

    public List<com.martinisztin.varazslak.model.Service> getAll() {
        return serviceRepository.findAll();
    }
}
