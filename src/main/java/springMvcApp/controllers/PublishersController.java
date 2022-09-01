package springMvcApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springMvcApp.dao.PublisherDAO;
import springMvcApp.models.Publisher;

@Controller
@RequestMapping("/publishers")
public class PublishersController {
    private final PublisherDAO publisherDAO;
    @Autowired
    public PublishersController(PublisherDAO publisherDAO) {
        this.publisherDAO = publisherDAO;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("publishers",publisherDAO.index());
        return "publishers/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("publisher",publisherDAO.show(id));
        return "publishers/show";
    }

    @GetMapping("/new")
    public String newPublisher(@ModelAttribute("publisher")Publisher publisher){
        return "publishers/new";
    }

    @PostMapping
    public String create(@ModelAttribute("publisher")Publisher publisher){
        publisherDAO.save(publisher);
        return "redirect:/publishers";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id,Model model){
        model.addAttribute("publisher",publisherDAO.show(id));
        return "publishers/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id")int id,@ModelAttribute("publisher")Publisher publisher){
        publisherDAO.update(id,publisher);
        return "redirect:/publishers";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        publisherDAO.delete(id);
        return "redirect:/publishers";
    }
}
