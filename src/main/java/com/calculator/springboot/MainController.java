package com.calculator.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class MainController {
    private final Logger logger = Logger.getLogger(String.valueOf(MainController.class));

    @GetMapping("/")
    public String showPage() {
        return "index";
    }

    @PostMapping("/")
    public String showAnswer(@RequestParam("a") double a,
                             @RequestParam("b") double b,
                             @RequestParam("c") double c,
                             @RequestParam(value = "d", required = false) String d,
                             Model model) {

        List<String> result = solveEquation(a, b, c);
        model.addAttribute("a", a);
        model.addAttribute("b", b);
        model.addAttribute("c", c);

        logger.info("a = " + a);
        logger.info("b = " + b);
        logger.info("c = " + c);
        logger.info("Значение флажка d: " + d);

        if (d != null)
            model.addAttribute("d", "Дискриминант: " + (b * b - 4 * a * c));
        if (result.get(0).equals("Нет корней"))
            model.addAttribute("result3", result.get(0));
        else if (result.get(0).equals(result.get(1)))
            model.addAttribute("result1", "x=" + result.get(0));
        else {
            model.addAttribute("result1", "x<sub>1</sub>=" + result.get(0));
            model.addAttribute("result2", "x<sub>2</sub>=" + result.get(1));
        }

        return "result";
    }

    public List<String> solveEquation(double a, double b, double c) {
        List<String> result = new ArrayList<>();
        double x1, x2;
        double d = b * b - 4 * a * c;
        if (d >= 0) {
            x1 = (-b + Math.sqrt(d)) / (2 * a);
            x2 = (-b - Math.sqrt(d)) / (2 * a);
            result.add(String.valueOf(x1));
            result.add(String.valueOf(x2));
        } else result.add("Нет корней");
        return result;
    }
}
