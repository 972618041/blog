package com.serend.blog.Controller;

import com.serend.blog.po.Tag;
import com.serend.blog.po.Type;
import com.serend.blog.service.TagService;
import com.serend.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    TagService typeService;

    @GetMapping("/tags")
    public String list(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                       Model model){
        model.addAttribute("page",typeService.listTag(pageable));
        return "admin/tags";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        model.addAttribute("type",typeService.getTag(id));
        return "admin/tags-input";
    }

    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("type",new Tag());
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String post(Tag type, RedirectAttributes attributes){
        Tag type2 = typeService.getTag(type.getName());
        if (type2!=null){
            attributes.addFlashAttribute("message","添加失败,类型重复");
        }else {
            Tag type1 = typeService.saveTag(type);
            if (type1 == null) {
                attributes.addFlashAttribute("message", "添加失败");
            }
            attributes.addFlashAttribute("message", "添加成功");
        }
        return "redirect:/admin/tags";
    }


    @PostMapping("/tags/{id}/input")
    public String editPost(Tag type, RedirectAttributes attributes,@PathVariable Long id){
        Tag type2 = typeService.getTag(type.getName());
        if (type2!=null){
            attributes.addFlashAttribute("message","更新失败,类型重复");
        }else {
            Tag type1 = typeService.updateTag(id, type);
            if (type1 == null) {
                attributes.addFlashAttribute("message", "更新失败");
            }
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }

}
