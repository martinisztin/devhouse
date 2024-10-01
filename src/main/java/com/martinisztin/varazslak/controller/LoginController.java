package com.martinisztin.varazslak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String getLogin(Model model) throws NoSuchAlgorithmException {

        // kréta style programming wink wink
        String[] random = new String[] {
                "A gyermekek mosolya a világ legnagyobb kincse.",
                "A boldogság titka, hogy lássuk a világot a gyermekek szemével.",
                "Egy boldog gyermek szívében lakozik a világ legszebb tündérmeséje.",
                "Gyermeknek lenni azt jelenti, hogy bármiből képes vagy csodát varázsolni.",
                "A gyermeki boldogság a szeretet legőszintébb formája.",
                "A gyermekek boldogsága olyan tiszta, mint a nyári napsütés.",
                "A gyermekek nevetése betölti a házat boldogsággal és fényességgel.",
                "A boldogság legigazibb formája egy gyermek ártatlan kacaja.",
                "Egy boldog gyermek mosolya képes eloszlatni a legnagyobb felhőket is.",
                "A gyermekkorban rejlő boldogság az élet egyik legnagyobb ajándéka.",
                "A gyermekek boldogsága a világ legszebb dallama.",
                "Egy boldog gyermek szívében mindig virágzik a szeretet kertje.",
                "A gyermeki boldogság olyan, mint egy pillangó, amely színessé varázsolja az életet.",
                "Minden gyermek egy külön kis világ, tele boldogsággal és álmokkal.",
                "A gyermekek nevetése a legszebb ajándék, amit a világnak adhatunk.",
                "A boldogság, amit egy gyermek szívében találunk, soha nem halványul el.",
                "Egy gyermek boldog mosolya fényt hoz a legsötétebb napokra is.",
                "A gyermeki boldogság a világ legédesebb és legtisztább öröme.",
                "Egy boldog gyermek lelkében mindig ott ragyog a nap.",
                "A gyermek boldogsága a legigazibb tükre annak, milyen csodálatos az élet."
        };

        model.addAttribute("quote", random[SecureRandom.getInstanceStrong().nextInt(0, 20)]);

        return "staff/login";
    }
}
