package com.martinisztin.varazslak.controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorsController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object exception = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            System.out.println("Hiba: " + statusCode);
            System.out.println(status);

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/404";
            }
            if(statusCode == HttpStatus.FORBIDDEN.value()) {
                return "error/403";
            }
        }

        if(exception != null) {
            Throwable throwable = (Throwable) exception;

            model.addAttribute("exception", throwable.getCause().toString().split(":")[1]);
        }

        return "error";
    }
}
