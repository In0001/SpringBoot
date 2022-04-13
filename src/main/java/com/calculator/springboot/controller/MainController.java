package com.calculator.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.logging.Logger;

@Controller
public class MainController {
    private final Logger logger = Logger.getLogger(String.valueOf(MainController.class));

    @GetMapping(value = {"/", "/index"})
    public String showInputWindow() {
        return "index";
    }

    @PostMapping(value = {"/", "/index"})
    public String getPage(@RequestParam("a") double a,
                          @RequestParam("b") double b,
                          @RequestParam("c") double c,
                          RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("a", a);
        redirectAttributes.addFlashAttribute("b", b);
        redirectAttributes.addFlashAttribute("c", c);

        logger.info("a = " + a);
        logger.info("b = " + b);
        logger.info("c = " + c);

        return "redirect:result";
    }
}
