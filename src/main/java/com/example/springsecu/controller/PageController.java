package com.example.springsecu.controller;

import com.example.springsecu.model.User;
import com.example.springsecu.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class PageController {
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/home")
    public String getHome(){
        return "home";
    }
    @GetMapping("/reg")
    public String getReg(Model model){
        model.addAttribute("newUser", new User());
        return "reg";
    }

    @PostMapping("/reg")
    public String saveUser(
            @ModelAttribute("newUser")
            User user
    ) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/login";
    }
}
