package springMvcApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springMvcApp.dao.PersonDAO;

@Controller
@RequestMapping("/test-batch")
public class BatchController {
    private final PersonDAO personDAO;
    @Autowired
    public BatchController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    @GetMapping
    public String index(){
        return "batch/index";
    }
    @GetMapping("/without")
    public String withoutBatch(){
        personDAO.testMultiUpdate();
        return "redirect:/people";
    }
    @GetMapping("/with")
    public String withBatch(){
        personDAO.testBatchUpdate();
        return "redirect:/people";
    }
}
