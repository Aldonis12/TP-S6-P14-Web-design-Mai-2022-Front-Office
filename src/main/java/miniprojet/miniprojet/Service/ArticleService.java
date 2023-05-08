package miniprojet.miniprojet.Service;

import miniprojet.miniprojet.DAO.HibernateDAO;
import miniprojet.miniprojet.Model.Actualite;
import miniprojet.miniprojet.Model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ArticleService {
    @Autowired
    HibernateDAO dao;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Article> AllArticle(){
        List<Article> liste = dao.findQuery(Article.class,"Select * from article  where cast(datePublication as date)<=cast(now() as date) and cast(datepublication as time)<=cast(now() as time)");
        return liste;
    }

    public List<Article> Recheche(String mot){
        Article article = new Article();
        List<Article> liste = dao.recherche(article, mot);
        return liste;
    }

    public static String GetTempsApres(LocalDateTime date) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(date, now);
        long seconds = duration.getSeconds();
        if (seconds < 60) {
            return "Il y a " + seconds + " secondes";
        } else if (seconds < 3600) {
            long minutes = duration.toMinutes();
            return "Il y a " + minutes + " minutes";
        } else if (seconds < 86400) {
            long hours = duration.toHours();
            return "Il y a " + hours + " heures";
        } else if (seconds < 604800) {
            long days = duration.toDays();
            return "Il y a " + days + " jours";
        } else if (seconds < 2592000) {
            long weeks = duration.toDays() / 7;
            return "Il y a " + weeks + " semaines";
        } else {
            long months = ChronoUnit.MONTHS.between(date, now);
            return "Il y a " + months + " mois";
        }
    }

    public List<Actualite> Actualite(){
        List<Object[]> results = entityManager.createNativeQuery("select * from actualite order by datecreation desc").getResultList();
        List<Actualite> actualites = new ArrayList<>();
        for (Object[] result : results) {
            Actualite actualite = new Actualite();
            Article article = new Article();
            actualite.setAnnonce((String) result[0]);
            Object date = dao.findForArticle("Select datepublication from article where idarticle="+result[1]);
            article.setDatepublication((Timestamp) date);

            if (date instanceof Timestamp) {
                Timestamp timestamp = (Timestamp) date;
                LocalDateTime localDateTime = timestamp.toLocalDateTime();
                String formattedDate = localDateTime.format(DateTimeFormatter.ofPattern("EEEE d MMMM yyyy à HH:mm", Locale.FRENCH));
                article.setTitre(formattedDate);
            }
            actualite.setArticle(article);
            Timestamp temps = (Timestamp) result[2];
            actualite.setDateCreation(GetTempsApres(temps.toLocalDateTime()));
            actualites.add(actualite);
        }
        return actualites;
    }
}
