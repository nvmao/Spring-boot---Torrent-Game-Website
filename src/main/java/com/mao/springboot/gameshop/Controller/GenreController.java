package com.mao.springboot.gameshop.Controller;

import com.mao.springboot.gameshop.Entity.Gerne;
import com.mao.springboot.gameshop.Service.GerneService;
import com.mao.springboot.gameshop.Service.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GerneService gerneService;

    @GetMapping()
    public String get(Model model){

        model.addAttribute("user", LoginUser.getLoginUser());
        model.addAttribute("genres",gerneService.findAll());

        return "/small/genres";
    }

    @PostMapping("/add")
    public String add(@RequestParam("name")String name){

        Gerne gerne = new Gerne();
        gerne.setName(name);

        gerneService.add(gerne);

        return "redirect:/genres";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id){

        gerneService.delete(id);

        return "redirect:/genres";
    }

}
