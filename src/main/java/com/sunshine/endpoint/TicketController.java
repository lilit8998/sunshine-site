package com.sunshine.endpoint;

import com.sunshine.model.Ticket;
import com.sunshine.repository.TicketRepo;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

@Controller
public class TicketController {
    private final TicketRepo ticketRepo;
@Value("${ticket.upload.dir}")
private String uploadDirTicket;

    public TicketController(TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;

    }

    @GetMapping("/tours")
    public String allTickets(ModelMap modelMap){
        List<Ticket> all = ticketRepo.findAll();
        modelMap.addAttribute("tickets",all);
        return "tours";
    }


    @GetMapping("ticket/delete")
    public String deleteTicket(@RequestParam("id") int id){
        ticketRepo.deleteById(id);
        return "redirect:/tours";
    }

    @GetMapping("edit/ticket")
    public String editTicket(@RequestParam("id") Integer id, ModelMap modelMap){

        if (id != 0) {
            Ticket one = ticketRepo.findById(id).get();
            modelMap.addAttribute("ticket", one);
        }
        return "addTicket";
    }

    @GetMapping("add/ticket")
    public String createTicket(ModelMap modelMap){
            modelMap.addAttribute("ticket", new Ticket());
       return "addTicket";
    }

  @PostMapping("/add/ticket")
    public String addTicket(@ModelAttribute Ticket ticket, @RequestParam("image") MultipartFile image) throws IOException {
      if (image != null){

          String photoUrl = System.currentTimeMillis() + "_"
              + "_" + image.getOriginalFilename();
          File fileTicket = new File( uploadDirTicket + photoUrl);

          image.transferTo(fileTicket);
          ticket.setPhotoUrlTicket(photoUrl);
      }
        ticketRepo.save(ticket);
        return "redirect:/tours";
    }

    @GetMapping("ticket/single/page")
    public String singlePage(@RequestParam("id") int id,ModelMap modelMap) {
        modelMap.addAttribute("ticket", ticketRepo.getOne(id));
        return "SingleTicketPage";
    }
    @GetMapping(value = "/ticket/image")
    public @ResponseBody
    byte[] getImage(@RequestParam("photoUrlTicket") String photoUrlTicket) throws IOException{
        InputStream in = new FileInputStream(uploadDirTicket + File.separator + photoUrlTicket);
        return IOUtils.toByteArray(in);
    }


}