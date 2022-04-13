package com.calculator.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class SolutionController {

    @GetMapping("/solution")
    public String showPage(@ModelAttribute("a") double a,
                           @ModelAttribute("b") double b,
                           @ModelAttribute("c") double c,
                           Model model) {

        if (a >= 0) {
            model.addAttribute("dA", a);
        }
        else {
            model.addAttribute("dA", "(" + a + ")");
        }

        if (b >= 0) {
            model.addAttribute("signB", "+");
            model.addAttribute("newB", b);
            model.addAttribute("dB", b);
        }
        else {
            model.addAttribute("newB", b);
            model.addAttribute("dB", "(" + b + ")");
        }

        if (c >= 0) {
            model.addAttribute("signC", "+");
            model.addAttribute("newC", c);
            model.addAttribute("dC", c);
        }
        else {
            model.addAttribute("newC", c);
            model.addAttribute("dC", "(" + c + ")");
        }

        double d = b * b - 4 * a * c;
        model.addAttribute("d", d);

        String s1 = "";
        String s2 = "";
        double x1 = (-b + Math.sqrt(d)) / (2 * a);
        double x2 = (-b - Math.sqrt(d)) / (2 * a);

        String b1, a1;
        if (b > 0)
            b1 = "-" + b;
        else
            b1 = "" + -b;

        if (a > 0)
            a1 = "" + a;
        else
            a1 = "(" + a + ")";

        if (d > 0) {
            s1 = "x1=(" + b1 + "+√" + d + ")/(2*" + a1 + ")=" + x1;
            s2 = "x2=(" + b1 + "-√" + d + ")/(2*" + a1 + ")=" + x2;
        } else if (d == 0) {
            s1 = "d=0 => уравнение имеет один корень";
            s2 = "x=" + b1 + "/(2*" + a1 + ")=" + x1;
        }
        else {
            s1 = "d<0 => корней нет";
        }
        model.addAttribute("result1", s1);
        model.addAttribute("result2", s2);

        return "solution";
    }
}
