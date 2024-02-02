package ru.mytyshi.esnp.controllers;

import jakarta.servlet.http.HttpServletResponse;
import ru.mytyshi.esnp.models.MainTable;
import ru.mytyshi.esnp.models.Person;
import ru.mytyshi.esnp.services.AuthService;
import ru.mytyshi.esnp.services.MainTableService;
import ru.mytyshi.esnp.services.PeopleService;
import ru.mytyshi.esnp.util.CsvFileGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/mainTable")
public class MainTableController {

    private final MainTableService mainTableService;

    private final PeopleService peopleService;

    private final AuthService authService;

    private final CsvFileGenerator csvFileGenerator;

    @Autowired
    public MainTableController(MainTableService mainTableService, PeopleService peopleService,
                               AuthService authService, CsvFileGenerator csvFileGenerator) {
        this.mainTableService = mainTableService;
        this.peopleService = peopleService;
        this.authService = authService;
        this.csvFileGenerator = csvFileGenerator;
    }

    @PostMapping()
    public String createRecord(@ModelAttribute ("mainTable") @Valid MainTable mainTable,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "mainTable/createRecord";
        }

        mainTableService.save(mainTable);
        return "redirect:/mainTable";
    }

    @GetMapping("/createRecord")
    public String getCreateRecord(@ModelAttribute("mainTable") MainTable mainTable)  {
        return "mainTable/createRecord";
    }

    @GetMapping()
    public String getMainTable(Model model) {
        Person authPerson = authService.getAuthPerson();
        model.addAttribute("main_tables", mainTableService.getAllMainTable());
        model.addAttribute("people", peopleService.getAllPerson());
        model.addAttribute("authPerson", authPerson);

        return "mainTable/mainTable";
    }

    @GetMapping("/{id}/edit")
    public String editOfMainTableRecord(Model model, @PathVariable("id") int id) {
        model.addAttribute("recordOfMainTable", mainTableService.findOne(id));
        return "mainTable/editRecord";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("recordOfMainTable") @Valid MainTable mainTable, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if(bindingResult.hasErrors())
            return "mainTable/editRecord";

        mainTableService.update(id, mainTable);
        return "redirect:/mainTable";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        mainTableService.delete(id);
        return "redirect:/mainTable";
    }

    @GetMapping("/exportToCsv")
    public void exportToCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setCharacterEncoding("CP1251");
        response.addHeader("Content-Disposition", "attachment; filename=\"esnp.csv\"");
        csvFileGenerator.writeMainTableToCsv(mainTableService.getAllMainTable(), response.getWriter());
    }
}
