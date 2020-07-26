package com.serend.blog.Controller;

import com.serend.blog.po.Type;
import com.serend.blog.service.BlogService;
import com.serend.blog.service.TypeService;
import com.serend.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    TypeService typeService;

    @Autowired
    BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        Model model,@PathVariable Long id){

        List<Type> types = typeService.listTypeTop(10000);
        if (id==-1){
            id = types.get(0).getId();
        }
        BlogQuery bl = new BlogQuery();
        bl.setTypeId(id);
        model.addAttribute("types",types);
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));
        model.addAttribute("page",blogService.listBlog(pageable,bl));
        model.addAttribute("active",id);
        return "types";
    }
}
