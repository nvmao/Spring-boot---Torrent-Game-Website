package com.mao.springboot.gameshop.Controller;

import com.mao.springboot.gameshop.Entity.Game;
import com.mao.springboot.gameshop.Entity.Photo;
import com.mao.springboot.gameshop.Service.FileService;
import com.mao.springboot.gameshop.Service.GameService;
import com.mao.springboot.gameshop.Service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/photos")
public class PhotoController {

    @Autowired
    GameService gameService;

    @Autowired
    PhotoService photoService;

    @Autowired
    FileService fileService;

    @PostMapping("/add")
    public String addPhoto(@RequestParam("gameId") int gameId, @RequestParam("photo")MultipartFile[] photoFiles){

        try {
            for(int i = 0; i < photoFiles.length;i++){
                fileService.saveImage(photoFiles[i]);
                String photoPath = "/uploads/img/" + photoFiles[i].getOriginalFilename();

                Game game = gameService.find(gameId);
                Photo photo = new Photo();

                photo.setLink(photoPath);
                photo.setGame(game);

                photoService.add(photo);
            }

        }catch (Exception e){
            System.out.println(e);
        }

        return "redirect:/games/"+gameId+"/edit";
    }

    @GetMapping("/{gameId}/delete/{id}")
    public String deleteGame(@PathVariable("gameId") int gameId, @PathVariable("id")int id){

        try {
            photoService.delete(id);

        }catch (Exception e){
            System.out.println(e);
        }

        return "redirect:/games/"+gameId+"/edit";
    }


}
