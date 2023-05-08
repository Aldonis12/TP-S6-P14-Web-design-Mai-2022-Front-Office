package miniprojet.miniprojet.Controller;

import miniprojet.miniprojet.Model.Article;
import miniprojet.miniprojet.Service.ArticleService;
import miniprojet.miniprojet.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;
    @PostMapping("LoginUtilisateur")
    private ModelAndView LoginSociete(@RequestParam("mail") String mail, @RequestParam("mdp") String mdp, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        try {
            int i = userService.LoginUser(mail, mdp);
            if (i==0){
                modelAndView.setViewName("../../index");
                modelAndView.addObject("alerte", "Email ou Mot de passe invalide !");
            } else{
                modelAndView.setViewName("User/ListArticle");
                List<Article> liste = articleService.AllArticle();
                modelAndView.addObject("liste", liste);
                session.setAttribute("id",i);
            }
        }catch (Exception e){
            modelAndView.setViewName("../../index");
            modelAndView.addObject("alerte", "Email ou Mot de passe invalide !");
        }
        return modelAndView;
    }

    @GetMapping("LogoutUtilisateur")
    public ModelAndView processLogout(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("../../index");
        session.invalidate();
        return modelAndView;
    }

    @ModelAttribute
    public void setNoCacheHeaders(HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
    }
}
