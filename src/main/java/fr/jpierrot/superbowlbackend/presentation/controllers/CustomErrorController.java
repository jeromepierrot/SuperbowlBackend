package fr.jpierrot.superbowlbackend.presentation.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomErrorController implements ErrorController {

    public String getErrorPath() {
        return "/error";
    }

    @GetMapping("/error")
    public ModelAndView errorHandler(HttpServletResponse response) {
        int errorCode = response.getStatus();

        System.out.println("--Error code: " + errorCode + " occured");

        return new ModelAndView("error");
    }
}
