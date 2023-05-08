package miniprojet.miniprojet.Service;

import miniprojet.miniprojet.DAO.HibernateDAO;
import miniprojet.miniprojet.Model.Actualite;
import miniprojet.miniprojet.Model.Article;
import miniprojet.miniprojet.Model.Favoris;
import miniprojet.miniprojet.Model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    HibernateDAO dao;

    @PersistenceContext
    private EntityManager entityManager;

    public int LoginUser(String mail, String mdp){
        int i = dao.LoginUser(mail, mdp);
        return i;
    }

    public void Inscription(String mail, String mdp){
        dao.Inscription(mail, mdp);
    }

    public void LikeArticle(int id, int idUser){
        dao.LikeArticle(id,idUser);
    }

    public void Dislike(int id,int idUser){
        dao.DisLike(id,idUser);
    }

    public List<Favoris> Favoris(int id){
        List<Object[]> results = entityManager.createNativeQuery("select * from Article where idarticle in (select idarticle from likearticle where iduser="+id+")").getResultList();
        List<Favoris> favoris = new ArrayList<>();
        for (Object[] result : results) {
            Favoris fav = new Favoris();
            Article article = new Article();
            article.setIdArticle((int) result[0]);
            article.setTitre((String) result[1]);
            article.setContenu((String) result[2]);
            article.setDatepublication((Timestamp) result[3]);
            article.setDatecreation((Timestamp) result[4]);
            fav.setArticle(article);
            favoris.add(fav);
        }
        return favoris;
    }
}
