package miniprojet.miniprojet.Controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import miniprojet.miniprojet.Model.Actualite;
import miniprojet.miniprojet.Model.Article;
import miniprojet.miniprojet.Model.Favoris;
import miniprojet.miniprojet.Service.ArticleService;
import miniprojet.miniprojet.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @GetMapping("InscriptionPage")
    public ModelAndView PageInscription(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("User/Inscription");
        return modelAndView;
    }

    @PostMapping("Inscription")
    public ModelAndView InscriptionUser(@RequestParam("mail") String mail, @RequestParam("mdp") String mdp){
        ModelAndView modelAndView = new ModelAndView();
        try {
            userService.Inscription(mail, mdp);
            modelAndView.setViewName("../../index");
        }catch (Exception e) {
            modelAndView.setViewName("User/Inscription");
        }
        return modelAndView;
    }

    @PostMapping("LikeArticle")
    private ModelAndView ArticleLike(@RequestParam("idArticle") int idArticle, @RequestParam("idUser") int idUser,  HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        try {
            userService.LikeArticle(idArticle,idUser);
            modelAndView.setViewName("User/ListArticle");
            List<Article> liste = articleService.AllArticle();
            modelAndView.addObject("liste", liste);
            session.setAttribute("id",idUser);
        }catch (Exception e){
            modelAndView.setViewName("User/ListArticle");
            List<Article> liste = articleService.AllArticle();
            modelAndView.addObject("liste", liste);
            session.setAttribute("id",idUser);
        }
        return modelAndView;
    }

    @PostMapping("DislikeArticle")
    private ModelAndView Dislike(@RequestParam("idArticle") int idArticle, @RequestParam("idUser") int idUser){
        ModelAndView modelAndView = new ModelAndView();
        try {
            userService.Dislike(idArticle,idUser);
            modelAndView.setViewName("redirect:/Favoris");
            modelAndView.addObject("id",idUser);
        }catch (Exception e){
            modelAndView.setViewName("redirect:/Favoris");
            modelAndView.addObject("id",idUser);
        }
        return modelAndView;
    }

    @GetMapping("/Recherche")
    public ModelAndView Recherche(@RequestParam("search") String sr,@RequestParam("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        try{
            List<Article> liste = articleService.Recheche(sr);
            modelAndView.setViewName("User/RechercheArticle");
            modelAndView.addObject("liste", liste);
            modelAndView.addObject("id",id);
        }catch (Exception e){e.printStackTrace();}
        return modelAndView;
    }

    @GetMapping("/Favoris")
    public ModelAndView Favoris(@RequestParam("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        try{
            List<Favoris> liste = userService.Favoris(id);
            modelAndView.setViewName("User/Favoris");
            modelAndView.addObject("id",id);
            modelAndView.addObject("liste", liste);
        }catch (Exception e){e.printStackTrace();}
        return modelAndView;
    }

    @GetMapping("/ListeArticle")
    public ModelAndView ListeArticle(@RequestParam("id") int id, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("User/ListArticle");
        List<Article> liste = articleService.AllArticle();
        modelAndView.addObject("liste", liste);
        session.setAttribute("id",id);
        return modelAndView;
    }

    @GetMapping("/notification")
    public ModelAndView Notification(@RequestParam("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("User/Notification");
        List<Actualite> liste = articleService.Actualite();
        modelAndView.addObject("liste", liste);
        modelAndView.addObject("id",id);
        return modelAndView;
    }
}
