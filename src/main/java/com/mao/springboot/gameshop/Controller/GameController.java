package com.mao.springboot.gameshop.Controller;

import com.mao.springboot.gameshop.Entity.*;
import com.mao.springboot.gameshop.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/games")
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @Autowired
    ReplyService replyService;

    @Autowired
    FileService fileService;

    @Autowired
    PublisherService publisherService;

    @Autowired
    private GerneService gerneService;

    @GetMapping()
    public String getAllGames(Model model){

        model.addAttribute("user", LoginUser.getLoginUser());

        Long maxPage = gameService.countGame() / 28 + 1;
        model.addAttribute("maxPage",maxPage);

        List<Gerne> geners = gerneService.findAll();
        model.addAttribute("geners",geners);


//        for(var game:gameService.findAll()){
//            game.setDownloadCount((int)(Math.random() * 2000));
//            game.setDownloadLink("/uploads/game/cyberpunk.torrent");
//            game.setDescription("<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>");
//
//            int randomGerne = (int)(Math.random() * geners.size());
//            if(!game.getGernes().contains(geners.get(randomGerne)))
//                game.getGernes().add(geners.get(randomGerne));
//
//            gameService.save(game);
//
//        }

        return "game/list-game";
    }

    @GetMapping("/add")
    public String addGame(Model model){
        model.addAttribute("game",new Game());
        model.addAttribute("genres",gerneService.findAll());
        model.addAttribute("user", LoginUser.getLoginUser());
        return "game/add-game";
    }

    @PostMapping("/add")
    public String AddGame(@ModelAttribute Game game,
                          @RequestParam("genre_tags") String genre_tags,
                          @RequestParam("publisher_id") Integer publisherId,
                          @RequestParam("pPhoto") MultipartFile pPhoto,
                          @RequestParam("hPhoto") MultipartFile hPhoto){

        try {
            fileService.saveImage(pPhoto);
            fileService.saveImage(hPhoto);

            String posterPhotoPath = "/uploads/img/"+pPhoto.getOriginalFilename();
            String hoverPhotoPath = "/uploads/img/"+hPhoto.getOriginalFilename();
            Publisher publisher = publisherService.find(publisherId);


            List<Gerne> genres = gerneService.find(genre_tags);

            game.setGernes(genres);
            game.setPublisher(publisher);
            game.setPosterPhoto(posterPhotoPath);
            game.setHoverPhoto(hoverPhotoPath);

            gameService.save(game);


        }catch (Exception e){
            System.out.println(e);
        }

        return "redirect:/games";
    }


    @GetMapping("/{id}")
    public String getGame(Model model,@PathVariable("id") int id){

        Game game = gameService.find(id);
        model.addAttribute("game",game);
        model.addAttribute("user", LoginUser.getLoginUser());

        return "game/game";
    }

    @GetMapping("/{id}/edit")
    public String editGame(Model model,@PathVariable("id") int id){
        Game game = gameService.find(id);
        model.addAttribute("game",game);
        model.addAttribute("genres",gerneService.findAll());
        model.addAttribute("user", LoginUser.getLoginUser());
        return "game/edit-game";
    }

    @GetMapping("/{id}/delete")
    public String deleteGame(Model model,@PathVariable("id") int id){
        gameService.delete(id);

        return "redirect:/games/";
    }



    @PostMapping("/{id}/edit")
    public String saveEditGame(@ModelAttribute Game game,
                               @RequestParam("gen_tags") String gen_tags,
                               @RequestParam("publisher_id") Integer publisherId,
                               @RequestParam("pPhoto") MultipartFile pPhoto,
                               @RequestParam("hPhoto") MultipartFile hPhoto){

        try{
            if(!hPhoto.isEmpty()){
                fileService.saveImage(hPhoto);
                String hoverPhotoPath = "/uploads/img/"+hPhoto.getOriginalFilename();
                game.setHoverPhoto(hoverPhotoPath);
            }
            if(!pPhoto.isEmpty()){
                fileService.saveImage(pPhoto);
                String posterPhotoPath = "/uploads/img/"+pPhoto.getOriginalFilename();
                game.setPosterPhoto(posterPhotoPath);
            }
            List<Gerne> genres = gerneService.find(gen_tags);
            game.setGernes(genres);

            Publisher publisher = publisherService.find(publisherId);
            game.setPublisher(publisher);

            gameService.save(game);
        }catch (Exception e){
            System.out.println(e);
        }

        return "redirect:/games/"+game.getId()+"/edit";
    }

    @PostMapping("/{id}/comment")
    public String addComment(@PathVariable("id") int gameId,@RequestParam("commentContent") String commentContent){

        Game game = gameService.find(gameId);
        User user = LoginUser.getLoginUser();
        Comment comment = new Comment();
        comment.setContent(commentContent);
        comment.setUser(user);
        comment.setGame(game);
        comment.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        commentService.add(comment);

        return "redirect:/games/"+gameId;
    }

    @PostMapping("/{id}/comment/reply")
    public String addReply(@PathVariable("id")int gameId,
            @RequestParam("replyContent") String replyContent,@RequestParam("commentId")int commentId){

        Reply reply = new Reply();
        reply.setContent(replyContent);
        reply.setComment(commentService.find(commentId));
        reply.setUser(LoginUser.getLoginUser());
        reply.setCreatedAt(new Timestamp(System.currentTimeMillis()));



        replyService.add(reply);

        return "redirect:/games/"+gameId;
    }


    @ModelAttribute("publishers")
    public List<Publisher> getAllPublisher(){
        return publisherService.findAll();
    }


}
