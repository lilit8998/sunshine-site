package com.sunshine.endpoint;

import com.sunshine.model.Post;
import com.sunshine.repository.PostRepo;
import com.sunshine.repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private PostRepo postRepo;
    private TicketRepo ticketRepo;

    public MainController(TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    @GetMapping("/")
    public String homePage(ModelMap modelMap) {
        List<Post> all = postRepo.findAll();
        modelMap.addAttribute("posts", all);
        return "index";
    }


    @GetMapping("/about")
    public String about(){
        return "about";
    }


    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }




}