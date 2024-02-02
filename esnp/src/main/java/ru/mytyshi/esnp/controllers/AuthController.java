package ru.mytyshi.esnp.controllers;

import ru.mytyshi.esnp.models.Person;
import ru.mytyshi.esnp.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/hello")
    public String helloPage(Model model)  {
        Person authPerson = authService.getAuthPerson();
        model.addAttribute("authPerson", authPerson);
        return "auth/hello";
    }
}