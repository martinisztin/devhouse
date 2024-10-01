package com.martinisztin.varazslak.controller;

import com.martinisztin.varazslak.utils.StringUtils;
import com.martinisztin.varazslak.model.City;
import com.martinisztin.varazslak.model.Worker;
import com.martinisztin.varazslak.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{city}/about")
public class AboutUsController {

    @Autowired
    private WorkerRepository workerRepository;
    @GetMapping("/{id}")
    public String getUserVac(@PathVariable String city, @PathVariable Long id, Model model) {
        String accentedCity = StringUtils.addKnownAccent(city);
        Worker worker = workerRepository.findById(id).orElseThrow();
        boolean worksThereOk = false;

        for(City workerCity : worker.getWorkplaces()) {
            if(workerCity.getName().equals(accentedCity)) {
                worksThereOk = true;
                break;
            }
        }

        if(!worksThereOk)
            return "redirect:/{city}/about";

        model.addAttribute("worker", worker);
        model.addAttribute("strippedCity", city);
        model.addAttribute("city", accentedCity);

        return "detailed-about";
    }
}
