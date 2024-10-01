package com.martinisztin.varazslak.controller;

import com.martinisztin.varazslak.dto.ServiceDTO;
import com.martinisztin.varazslak.model.Service;
import com.martinisztin.varazslak.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ModifyServiceController {
    @Autowired
    private ServiceRepository serviceRepository;

    @PostMapping("/modify-service")
    public String postModifyService(@ModelAttribute ServiceDTO serviceDTO, RedirectAttributes redirectAttributes) {
        Service service = new Service();

        service.setId(serviceDTO.getId());
        service.setName(serviceDTO.getName());
        service.setDescription(serviceDTO.getDescription());
        service.setForm(serviceDTO.getForm());
        service.setAgeRange(serviceDTO.getAgeRange());
        service.setDuration(serviceDTO.getDuration());
        service.setPrice(serviceDTO.getPrice());

        serviceRepository.save(service);

        return "redirect:/staff/manage-services";
    }
}
