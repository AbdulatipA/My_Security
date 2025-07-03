package org.example.my_security.controller;

import lombok.AllArgsConstructor;
import org.example.my_security.User;
import org.example.my_security.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1")
@AllArgsConstructor
public class AuthorizationController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping()
    public String homePage() {
        return "homePage";
    }

    @GetMapping("/profile")
    public String showProfile(Model model) {
        String username = "admin";
        User user = userService.findByUsername(username);

        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/reg")
    public String regForm(Model model) {
        return "reg";
    }
}
