package com.martinisztin.varazslak.controller;

import com.martinisztin.varazslak.model.User;
import com.martinisztin.varazslak.service.UserDetailsImpl;
import com.martinisztin.varazslak.service.UserService;
import com.martinisztin.varazslak.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ChangePasswordController {

    @Autowired
    private UserService userService;

    @PostMapping("/change-password")
    public String postChangePassword(@RequestParam("oldPassword") String oldPassword,
                                     @RequestParam("newPassword1") String newPassword,
                                     @RequestParam("newPassword2") String newPasswordAgain,
                                     RedirectAttributes redirectAttributes) {

        UserDetailsImpl currentUser = UserUtils.getUserFromAuth();

        if (!userService.passwordMatchesWithDb(currentUser.getUsername(), oldPassword)) {
            redirectAttributes.addFlashAttribute("danger", "Helytelen aktuális jelszót adtál meg.");
            return "redirect:/staff/change-password";
        }

        if (!newPassword.equals(newPasswordAgain)) {
           redirectAttributes.addFlashAttribute("danger", "A két új jelszó nem egyezik meg.");
           return "redirect:/staff/change-password";
        }

        if (userService.updatePassword(currentUser.getUsername(), newPassword) != null) {
            redirectAttributes.addFlashAttribute("success", "Jelszó változtatás sikeres!");
        } else {
            redirectAttributes.addFlashAttribute("danger", "A jelszó megváltoztatása meghiúsult.");
        }

        return "redirect:/staff/change-password";
    }
}
