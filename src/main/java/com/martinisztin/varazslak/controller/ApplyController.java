package com.martinisztin.varazslak.controller;


import com.martinisztin.varazslak.utils.StringUtils;
import com.martinisztin.varazslak.dto.ApplicationDTO;
import com.martinisztin.varazslak.model.Application;
import com.martinisztin.varazslak.repository.ApplicationRepository;
import com.martinisztin.varazslak.service.CityService;
import com.martinisztin.varazslak.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ApplyController {

    @Autowired
    private CityService cityService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ApplicationRepository applicationRepository;

    @PostMapping("/apply")
    public String postApply(@ModelAttribute ApplicationDTO applicationForm, Model model) {
        Application application = new Application();

        //TODO: precheck all necessary form data
        String strippedCity = StringUtils.stripAccents(applicationForm.getCity()).toLowerCase();

        application.setCity(cityService.getCityByName(applicationForm.getCity()));
        application.setParentName(applicationForm.getParentName());
        application.setParentEmail(applicationForm.getParentEmail());
        application.setParentPhone(applicationForm.getParentPhone());
        application.setService(serviceService.getServiceById(applicationForm.getService()));
        application.setChildName(applicationForm.getChildName());
        application.setChildAge(applicationForm.getChildAge());
        application.setAdditionalInfo(applicationForm.getAdditionalInfo());

        applicationRepository.save(application);

        return "redirect:/" + strippedCity + "/apply";
    }
}
