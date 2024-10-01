package com.martinisztin.varazslak.controller;

import com.martinisztin.varazslak.model.Role;
import com.martinisztin.varazslak.model.User;
import com.martinisztin.varazslak.repository.RoleRepository;
import com.martinisztin.varazslak.service.UserService;
import com.martinisztin.varazslak.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsergenController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;


    @PostMapping("/generate-user")
    public String postUserGen(@RequestParam("username") String username,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("firstName") String firstName,
                              RedirectAttributes redirectAttributes) {

        if (userService.isUsernameTaken(username)) {
            redirectAttributes.addFlashAttribute("danger", username + " felhasználónév már foglalt.");
            return "redirect:/staff/usergen";
        }

        User user = new User();
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEnabled(true);


        user.setRole(roleRepository.findById(1L).orElseThrow());

        String genPassword = userService.createUser(user);

        System.out.println("[LOG]: User added:" + user.getUsername() + " - " + genPassword);

        redirectAttributes.addFlashAttribute("uname", user.getUsername());
        redirectAttributes.addFlashAttribute("success", genPassword);

        return "redirect:/staff/usergen";
    }
}
