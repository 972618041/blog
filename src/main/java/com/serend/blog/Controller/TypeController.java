package com.serend.blog.Controller;

import com.serend.blog.po.Type;
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
public class TypeController {

    @Autowired
    TypeService typeService;

    @GetMapping("/types")
    public String list(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                       Model model){
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String post(Type type, RedirectAttributes attributes){
        Type type2 = typeService.getType(type.getName());
        if (type2!=null){
            attributes.addFlashAttribute("message","添加失败,类型重复");
        }else {
            Type type1 = typeService.saveType(type);
            if (type1 == null) {
                attributes.addFlashAttribute("message", "添加失败");
            }
            attributes.addFlashAttribute("message", "添加成功");
        }
        return "redirect:/admin/types";
    }


    @PostMapping("/types/{id}/input")
    public String editPost(Type type, RedirectAttributes attributes,@PathVariable Long id){
        Type type2 = typeService.getType(type.getName());
        if (type2!=null){
            attributes.addFlashAttribute("message","更新失败,类型重复");
        }else {
            Type type1 = typeService.updateType(id, type);
            if (type1 == null) {
                attributes.addFlashAttribute("message", "更新失败");
            }
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }

}
