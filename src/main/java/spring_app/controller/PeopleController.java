package spring_app.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring_app.models.Person;
import spring_app.services.BooksService;
import spring_app.services.PeopleService;
import spring_app.util.PersonValidator;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;// внедряем слой сервис так как в контроллере мы не работаем с репозиторием напрямую
    private final PersonValidator personValidator;
    private final BooksService booksService;

    @Autowired
    public PeopleController(PeopleService peopleService, PersonValidator personValidator, BooksService booksService) {
        this.peopleService = peopleService;
        this.personValidator = personValidator;
        this.booksService = booksService;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        //получим всех людей из DAO и передадим на представление теймлифу
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));
        //получаем человека по айди из DAO и передадим его пна представление
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/new";
        } else {
            peopleService.save(person);
            return "redirect:/people";
        }
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findOne(id));
        return "/people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/edit";
        } else {
            peopleService.update(id, person);
            return "redirect:/people";
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }
}
