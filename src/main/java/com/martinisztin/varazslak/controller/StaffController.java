package com.martinisztin.varazslak.controller;


import com.martinisztin.varazslak.dto.ServiceDTO;
import com.martinisztin.varazslak.model.Application;
import com.martinisztin.varazslak.model.Service;
import com.martinisztin.varazslak.repository.ApplicationRepository;
import com.martinisztin.varazslak.service.ServiceService;
import com.martinisztin.varazslak.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/")
    public String getDashboard(Model model) {

        model.addAttribute("user", UserUtils.getUserFromAuth());

        return "staff/dashboard";
    }

    @GetMapping("/applications")
    public String getApplications(Model model) {
        List<Application> applicationList = new ArrayList<>();

        for(Application a : applicationRepository.findAll()) {
            if(a.isResolved())
                continue;
            applicationList.add(a);
        }

        model.addAttribute("applications", applicationList);

        return "staff/applications";
    }

    @GetMapping("/resolved-applications")
    public String getResolvedApplications(Model model) {
        List<Application> applicationList = new ArrayList<>();

        for(Application a : applicationRepository.findAll()) {
            if(!a.isResolved())
                continue;
            applicationList.add(a);
        }

        model.addAttribute("applications", applicationList);

        return "staff/resolved-applications";
    }

    @GetMapping("/resolve/{id}")
    public String getResolveApplication(@PathVariable Long id, Model model) {
        Application application = applicationRepository.findById(id).orElseThrow();
        application.setResolved(true);

        applicationRepository.save(application);

        return "redirect:/staff/applications";
    }

    @GetMapping("/usergen")
    public String getUserGenPage() {

        return "staff/usergen";
    }

    @GetMapping("/manage-services")
    public String getMngServicesPage(Model model) {
        List<Service> services = serviceService.getAll();

        model.addAttribute("services", services);

        return "staff/manage-services";
    }

    @GetMapping("/manage-service/{id}")
    public String getModifyServicePage(@PathVariable Long id, Model model) {
        Service service = serviceService.getServiceById(id);
        ServiceDTO serviceForm = new ServiceDTO();

        serviceForm.setId(service.getId());
        serviceForm.setName(service.getName());
        serviceForm.setDescription(service.getDescription());
        serviceForm.setForm(service.getForm());
        serviceForm.setAgeRange(service.getAgeRange());
        serviceForm.setDuration(service.getDuration());
        serviceForm.setPrice(service.getPrice());

        model.addAttribute("serviceForm", serviceForm);
        model.addAttribute("service", service);
        return "staff/modify-service";
    }

    @GetMapping("/change-password")
    public String getChangePassword(Model model) {
        return "staff/password-change";
    }
}
