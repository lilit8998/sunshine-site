package com.sunshine.endpoint;


import com.sunshine.model.Post;
import com.sunshine.repository.PostRepo;
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

@Controller
public class PostController {
    private final PostRepo postRepo;
    @Value("${post.upload.dir}")
    private String uploadDir;

    public PostController(PostRepo postRepo) {


        this.postRepo = postRepo;
    }

    @GetMapping("/post")
    public String getAllPost(ModelMap modelMap) {
        List<Post> all = postRepo.findAll();
        modelMap.addAttribute("posts", all);
        return "post";
    }

    @PostMapping("/add/post")
    public String addPost(@ModelAttribute Post post, @RequestParam("image") MultipartFile imagePost) throws IOException {
        if (imagePost != null) {
            String photoUrl = System.currentTimeMillis() +
                    "_" + imagePost.getOriginalFilename();
            File file = new File(uploadDir +photoUrl);
            imagePost.transferTo(file);
            post.setPhotoUrl(photoUrl);
        }
        postRepo.save(post);
        return "redirect:/post";
    }

    @GetMapping("add/post")
    public String createPost(ModelMap modelMap) {
        modelMap.addAttribute("post", new Post());
        return "addPost";
    }
    @GetMapping("post/single/page")
    public String singlePage(@RequestParam("id") int id,ModelMap modelMap) {
        modelMap.addAttribute("post", postRepo.getOne(id));
        return "SinglePostPage";
    }

    @GetMapping("edit/post")
    public String editPost(@RequestParam("id") int id, ModelMap modelMap) {

        if (id != 0) {
            modelMap.addAttribute("post", postRepo.getOne(id));
        }
        return "addPost";
    }

    @GetMapping("post/delete")
    public String deletePost(@RequestParam("id") int id) {
        postRepo.deleteById(id);
        return "redirect:/post";
    }

    @GetMapping(value = "/post/image")
    public @ResponseBody
    byte[] getImage(@RequestParam("photoUrl") String photoUrlPost) throws IOException {

        InputStream in = new FileInputStream(uploadDir + photoUrlPost);
        return IOUtils.toByteArray(in);
    }

}
