package ru.mytyshi.esnp.controllers;

import org.springframework.http.ResponseEntity;
import ru.mytyshi.esnp.models.Person;
import ru.mytyshi.esnp.services.PeopleService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import jakarta.validation.Valid;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person) {
        return "people/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person,
                                      BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            return "people/registration";

        peopleService.registration(person);
        return "redirect:/auth/hello";
    }

    @GetMapping("/allPeople")
    public String allPeople(Model model) {
        List<Person> people = peopleService.getAllPerson();
        model.addAttribute("people", people);
        return "people/allPeople";
    }
}
