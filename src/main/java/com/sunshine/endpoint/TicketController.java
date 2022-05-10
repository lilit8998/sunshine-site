package com.sunshine.endpoint;

import com.sunshine.model.Post;
import com.sunshine.model.Ticket;
import com.sunshine.repository.PostRepo;
import com.sunshine.repository.TicketRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TicketController {
    private final TicketRepo ticketRepo;
    private final PostRepo postRepo;

    public TicketController(TicketRepo ticketRepo, PostRepo postRepo) {
        this.ticketRepo = ticketRepo;
        this.postRepo = postRepo;
    }

    @GetMapping("/tours")
    public String ticket(ModelMap modelMap){
        List<Ticket> all = ticketRepo.findAll();
        modelMap.addAttribute("tickets",all);
        return "tours";
    }

    @GetMapping("ticket/delete")
    public String deleteTicket(@RequestParam("id") int id){
        ticketRepo.deleteById(id);
        return "redirect:tours";
    }

    @GetMapping("addTicket")
    public String saveTicket(@RequestParam("id") Integer id, ModelMap modelMap){

        if (id != 0) {
            modelMap.addAttribute("ticket", ticketRepo.getOne(id));
        }
        return "addTicket";
    }


    @PostMapping("/addTicket")
    public String addTicket(@ModelAttribute Ticket ticket){
        ticketRepo.save(ticket);
        return "redirect:tours";
    }

}