package com.sunshine.endpoint;


import com.sunshine.model.Post;
import com.sunshine.model.Ticket;
import com.sunshine.repository.PostRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController {
    private final PostRepo postRepo;

    public PostController(PostRepo postRepo) {


        this.postRepo = postRepo;
    }
    @GetMapping("/post")
    public String ticket(ModelMap modelMap){
        List<Post> all = postRepo.findAll();
        modelMap.addAttribute("posts",all);
        return "post";
    }

    @PostMapping("/addPost")
    public String addPost(@ModelAttribute Post post){
        postRepo.save(post);
        return "redirect:/";
    }

    @GetMapping("addPost")
    public String savePost(@RequestParam("id") int id, ModelMap modelMap){

        if (id != 0) {
            modelMap.addAttribute("post", postRepo.getOne(id));
        }
        return "addPost";
    }

    @GetMapping("post/delete")
    public String deletePost(@RequestParam("id") int id){
        postRepo.deleteById(id);
        return "redirect:/";
    }



   



}
