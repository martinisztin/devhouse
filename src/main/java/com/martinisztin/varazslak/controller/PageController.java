package com.martinisztin.varazslak.controller;


import com.martinisztin.varazslak.utils.StringUtils;
import com.martinisztin.varazslak.dto.ApplicationDTO;
import com.martinisztin.varazslak.model.City;
import com.martinisztin.varazslak.model.Service;
import com.martinisztin.varazslak.model.Worker;
import com.martinisztin.varazslak.repository.CityRepository;
import com.martinisztin.varazslak.repository.WorkerRepository;
import com.martinisztin.varazslak.service.CityService;
import com.martinisztin.varazslak.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// common controller for all the get mappings (basically just to print the pages)
@Controller
@RequestMapping("/{city}")
public class PageController {

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private CityService cityService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public String getCityHome(@PathVariable String city, Model model) {

        model.addAttribute("password", bCryptPasswordEncoder.encode("n16645"));
        model.addAttribute("strippedCity", city);
        model.addAttribute("city", StringUtils.addKnownAccent(city));

        return "home";
    }

    @GetMapping("/about")
    public String getAboutPage(@PathVariable String city, Model model) {
        String accentedCity = StringUtils.addKnownAccent(city);
        List<Worker> workers = null;

        for(City c : cityRepository.findAll()) {
            if(c.getName().equals(accentedCity))
                workers = workerRepository.findWorkersByCityId(c.getId());
        }

        model.addAttribute("workers", workers);
        model.addAttribute("strippedCity", city);
        model.addAttribute("city", accentedCity);

        return "about";
    }


    @GetMapping("/apply")
    public String getApplyPage(@PathVariable String city, Model model) {
        String accentedCity = StringUtils.addKnownAccent(city);
        List<Service> services = serviceService.getServicesForCity(accentedCity);

        ApplicationDTO application = new ApplicationDTO();

        application.setCity(cityService.getCityByName(accentedCity).getName());

        model.addAttribute("applyForm", application);
        model.addAttribute("services", services);
        model.addAttribute("city", accentedCity);
        model.addAttribute("strippedCity", city);

        return "apply";
    }

    @GetMapping("/services")
    public String getServices(@PathVariable String city, Model model) {
        String accentedCity = StringUtils.addKnownAccent(city);

        List<Service> services = serviceService.getDistinctServicesForCity(accentedCity);
        Service service = serviceService.getServiceById(15L);
        services.remove(service);

        service = serviceService.getServiceById(14L);
        service.setName(service.getName() + "/ terápia");

        model.addAttribute("services", services);
        model.addAttribute("city", accentedCity);
        model.addAttribute("strippedCity", city);

        return "services";
    }

    @GetMapping("/rules")
    public String getRulesPage(@PathVariable String city, Model model) {
        String accentedCity = StringUtils.addKnownAccent(city);

        model.addAttribute("city", accentedCity);
        model.addAttribute("strippedCity", city);

        return "rules";
    }

    @GetMapping("/prices")
    public String getPricesPage(@PathVariable String city, Model model) {
        String accentedCity = StringUtils.addKnownAccent(city);


        List<Service> services = new ArrayList<>();

        for(Service s : serviceService.getServicesForCity(accentedCity)) {
            if(s.getName().contains("(")) {
                s.setName(s.getName().split("\\(")[0]);
            }
            services.add(s);
        }

        model.addAttribute("services", services);
        model.addAttribute("city", accentedCity);
        model.addAttribute("strippedCity", city);

        return "prices";
    }

    @GetMapping("/rooms")
    public String getRooms(@PathVariable String city, Model model) {
        String accentedCity = StringUtils.addKnownAccent(city);

        Map<String, List<String>> rooms = new HashMap<>();

        rooms.put("Kék", new ArrayList<>());
        rooms.put("Piros", new ArrayList<>());
        rooms.put("Zöld", new ArrayList<>());
        rooms.put("Sárga", new ArrayList<>());

        List<String> roomFiles = new ArrayList<>();

        roomFiles.add("1.jpg");
        roomFiles.add("2.jpg");
        roomFiles.add("3.jpg");
        roomFiles.add("4.jpg");

        rooms.get("Kék").addAll(roomFiles);
        rooms.get("Piros").addAll(roomFiles);
        rooms.get("Zöld").addAll(roomFiles.subList(0, 2));
        rooms.get("Sárga").addAll(roomFiles.subList(0, 2));


        model.addAttribute("rooms", rooms);
        model.addAttribute("city", accentedCity);
        model.addAttribute("strippedCity", city);

        return "rooms";
    }

}
