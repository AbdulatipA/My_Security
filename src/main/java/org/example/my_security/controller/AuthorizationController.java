package org.example.my_security.controller;

import lombok.AllArgsConstructor;
import org.example.my_security.User;
import org.example.my_security.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

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
    public String showProfile(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);

        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/editProfile")
    public String editProfile(@ModelAttribute("user") User user, Principal principal) {
        User currentUser = userService.findByUsername(principal.getName());

        user.setId(currentUser.getId());

        userService.update(user);
        return "redirect:/api/v1/profile";
    }


    @GetMapping("/editProfile")
    public String editProfile(Model model, Principal principal) {
        String username = principal.getName();
        User currentUser = userService.findByUsername(username);

        model.addAttribute("user", currentUser);
        return "editProfile";
    }
}
