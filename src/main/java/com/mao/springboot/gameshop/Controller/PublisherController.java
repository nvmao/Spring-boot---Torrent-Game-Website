package com.mao.springboot.gameshop.Controller;

import com.mao.springboot.gameshop.Entity.Publisher;
import com.mao.springboot.gameshop.Service.LoginUser;
import com.mao.springboot.gameshop.Service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping()
    public String get(Model model){

        model.addAttribute("user", LoginUser.getLoginUser());
        model.addAttribute("publishers",publisherService.findAll());

        return "small/publisher";
    }

    @PostMapping("/add")
    public String add(@RequestParam("name") String name){
        Publisher publisher = new Publisher();
        publisher.setName(name);
        publisherService.add(publisher);
        return "redirect:/publishers";
    }

    @GetMapping("{id}/delete")
    public String add(@PathVariable("id") int id){

        publisherService.delete(id);
        return "redirect:/publishers";
    }

}
