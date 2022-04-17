package com.calculator.springboot.controller;

import com.calculator.springboot.entity.ERole;
import com.calculator.springboot.entity.Role;
import com.calculator.springboot.entity.User;
import com.calculator.springboot.repository.RoleRepository;
import com.calculator.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
public class RegistrationController {
    @GetMapping("/registration")
    public String showPage() {
        return "registration";
    }

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/registration")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("passwordConfirmation") String passwordConfirmation,
                               @RequestParam("roles") String roleParameter,
                               Model model) {

        if (userRepository.existsByUsername(username)) {
            model.addAttribute("usernameError", "Пользователь с таким логином уже существует!");
            return "registration";
        }

        if (!password.equals(passwordConfirmation)) {
            model.addAttribute("passwordError", "Пароли не совпадают!");
            return "registration";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));

        Role userRole;
        ERole erole;

        if (roleParameter.equals("user")) {
            erole = ERole.ROLE_USER;
        }
        else
            erole = ERole.ROLE_PREMIUM;

        if (!roleRepository.existsByName(erole))
            createRole(erole);

        userRole = roleRepository.findByName(erole);

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        user.setRoles(roles);
        userRepository.save(user);

        model.addAttribute("successfulRegistration", "Пользователь зарегистрирован");

        return "registration";
    }

    void createRole(ERole role) {
        roleRepository.save(new Role(role));
    }
}
