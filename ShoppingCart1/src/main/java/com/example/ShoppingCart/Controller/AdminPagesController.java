package com.example.ShoppingCart.Controller;

import com.example.ShoppingCart.PageRepository;
import com.example.ShoppingCart.models.data.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("admin/pages")
public class AdminPagesController {
    @Autowired
    PageRepository repo;

@GetMapping
public String index(Model model){

    List<Pages> pages= repo.findAll();
    System.out.println("size is" +pages.size());
    model.addAttribute("pages",pages);
    return "/admin/pages/index";


}
@GetMapping ("/add")
    public String add(Model model){
    model.addAttribute("page",new Pages());
    System.out.println("reached");
    return "admin/pages/add";
}

@PostMapping("/add")
    public String add(Pages page,BindingResult bindingResult , RedirectAttributes redirectAttributes,Model model){
    redirectAttributes.addFlashAttribute("message","Page Added");
    redirectAttributes.addFlashAttribute("alertClass","alert-success");

    String slug=page.getSlug()==""?page.getTitle().toLowerCase().replace(" ","-"):page.getSlug().toLowerCase().replace(" ","-");
    Pages slugExists=  repo.findBySlug(slug);
    if(slugExists!=null){
       redirectAttributes.addFlashAttribute("message","slug exists ,choose another");
        redirectAttributes.addFlashAttribute("alertClass","alert-danger");
        redirectAttributes.addFlashAttribute("page",page);
    }
    else{
       page.setSlug(slug);
        page.setSorting(100);

        repo.save(page);
    }
    return "redirect:/admin/pages/add";
}
@GetMapping("/edit/{id}")
    public String editPage(@PathVariable int id,Model model){
    Pages page =repo.getOne(id);
    model.addAttribute("page",page);

    return "admin/pages/edit";
}
    @PostMapping("/edit")
    public String editPost(Pages page,BindingResult bindingResult , RedirectAttributes redirectAttributes,Model model){
        redirectAttributes.addFlashAttribute("message","Page Added");
        redirectAttributes.addFlashAttribute("alertClass","alert-success");

        String slug=page.getSlug()==""?page.getTitle().toLowerCase().replace(" ","-"):page.getSlug().toLowerCase().replace(" ","-");
        Pages slugExists=  repo.findBySlug(slug,page.getId());
        if(slugExists!=null){
            redirectAttributes.addFlashAttribute("message","slug exists ,choose another");
            redirectAttributes.addFlashAttribute("alertClass","alert-danger");
            redirectAttributes.addFlashAttribute("page",page);
        }
        else{
            page.setSlug(slug);


            repo.save(page);
        }
        return "redirect:/admin/pages/edit/"+ page.getId();
    }
    @GetMapping ("/delete/{id}")
    public String editPage(@PathVariable int id,RedirectAttributes redirectAttributes){
    repo.deleteById(id);
        redirectAttributes.addFlashAttribute("message","Page Deleted");
        redirectAttributes.addFlashAttribute("alertClass","alert-danger");

        return "redirect:/admin/pages/";
    }
}
