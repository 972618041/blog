package com.serend.blog.Controller;

import com.serend.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArchivesShowController {

    @Autowired
    BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model){
        model.addAttribute("blogCount",blogService.countBlog());
        model.addAttribute("archiveMap",blogService.archiveBlog());
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));
        return "archives";
    }
}
