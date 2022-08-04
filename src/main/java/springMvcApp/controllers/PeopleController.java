package springMvcApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springMvcApp.dao.PersonDAO;
import springMvcApp.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;
    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    //URL такой же /people REST URL
    @GetMapping
    public String index(Model model){
        //получим всех людей из ДАО и передадим в представление
        model.addAttribute("people",personDAO.index());
        return "people/index";
    }
    //REST URL
    @GetMapping("/{id}")
    public String show(
            //извлекает id из URL и помещает в аргументы метода, чтобы был доступ к id внутри метода
            @PathVariable("id")int id,
                       Model model){
        //получим одного человека по id из ДАО и передадим в представление
        model.addAttribute("person",personDAO.show(id));
        return "people/show";
    }
    @GetMapping("/new")
    public String newPerson(
            //создает новый объект Person по пустому конструктору и добавляет его в модель
            @ModelAttribute("person")Person person){
        return "people/new";
    }
    @PostMapping
    public String create(
            //создает новый объект Person (данные полей берет из URL) и добавляет его в модель
            @ModelAttribute("person")Person person){
        personDAO.save(person);
        return "redirect:/people";
    }
    //помещает данные в модели всех методов контроллера
    @ModelAttribute("message")
    public String message(){
        return "Don't forget!";
    }
}
