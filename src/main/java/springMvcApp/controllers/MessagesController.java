package springMvcApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springMvcApp.dao.MessageDAO;
import springMvcApp.models.Message;

@Controller
@RequestMapping("/messages")
public class MessagesController {
    private final MessageDAO messageDAO;
    @Autowired
    public MessagesController(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    //READ
    @GetMapping
    public String index(Model model){
        model.addAttribute("messages",messageDAO.index());
        return "messages/index";
    }
    //READ
    @GetMapping("/{id}")
    public String show(@PathVariable("id")int id,Model model){
        model.addAttribute("message",messageDAO.show(id));
        return "messages/show";
    }
    // show new message form
    @GetMapping("/new")
    public String newMessage(@ModelAttribute("message")Message message){
        return "messages/new";
    }
    //send POST from /new
    @PostMapping
    public String create(@ModelAttribute("message")Message message){
        messageDAO.save(message);
        return "redirect:/messages";
    }
    //show edit message form
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id")int id,Model model){
        model.addAttribute("message",messageDAO.show(id));
        return "messages/edit";
    }
    //send UPDATE(PATCH)from /id/edit
    @PatchMapping("/{id}")
    public String update(@PathVariable("id")int id,@ModelAttribute("message")Message message){
        messageDAO.update(id,message);
        return "redirect:/messages";
    }
    //DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id){
        messageDAO.delete(id);
        return "redirect:/messages";
    }
}
