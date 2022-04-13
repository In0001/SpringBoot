package com.calculator.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ResultController {

    double a, b, c;

    @GetMapping("/result")
    public String showAnswer(@ModelAttribute("a") double firstCoefficient,
                             @ModelAttribute("b") double secondCoefficient,
                             @ModelAttribute("c") double thirdCoefficient,
                             Model model) {

        a = firstCoefficient;
        b = secondCoefficient;
        c = thirdCoefficient;

        List<String> result = solveEquation(a, b, c);
        model.addAttribute("a", a);

        if (b >= 0) {
            model.addAttribute("signB", "+");
            model.addAttribute("newB", b);
        }
        else {
            model.addAttribute("newB", b);
        }

        if (c >= 0) {
            model.addAttribute("signC", "+");
            model.addAttribute("newC", c);
        }
        else {
            model.addAttribute("newC", c);
        }

        if (result.get(0).equals("Нет корней"))
            model.addAttribute("result3", result.get(0));
        else if (result.get(0).equals(result.get(1)))
            model.addAttribute("result0", result.get(0));
        else {
            model.addAttribute("result1", result.get(0));
            model.addAttribute("result2", result.get(1));
        }
        return "result";
    }

    @PostMapping("/result")
    public String getPage(RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("a", a);
        redirectAttributes.addFlashAttribute("b", b);
        redirectAttributes.addFlashAttribute("c", c);

        return "redirect:solution";
    }

    public List<String> solveEquation(double a, double b, double c) {
        List<String> answer = new ArrayList<>();
        double x1, x2;
        double d = b * b - 4 * a * c;
        if (d >= 0) {
            x1 = (-b + Math.sqrt(d)) / (2 * a);
            x2 = (-b - Math.sqrt(d)) / (2 * a);
            answer.add(String.valueOf(x1));
            answer.add(String.valueOf(x2));
        } else answer.add("Нет корней");
        return answer;
    }
}
