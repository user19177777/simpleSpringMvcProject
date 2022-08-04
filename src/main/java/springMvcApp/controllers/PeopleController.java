package springMvcApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import springMvcApp.dao.PersonDAO;

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
}
