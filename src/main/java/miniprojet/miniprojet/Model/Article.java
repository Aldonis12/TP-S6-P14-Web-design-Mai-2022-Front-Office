package miniprojet.miniprojet.Model;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idArticle", nullable = false)
    private int idArticle;
    @Column(name = "titre")
    private String titre;

    @Column(name = "contenu")
    private String contenu;

    @Column(name = "datepublication")
    private Timestamp datepublication;

    @Column(name = "datecreation", insertable = false)
    private Timestamp datecreation;

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Timestamp getDatepublication() {
        return datepublication;
    }

    public void setDatepublication(Timestamp datepublication) {
        this.datepublication = datepublication;
    }

    public Timestamp getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Timestamp datecreation) {
        this.datecreation = datecreation;
    }

    public String getUrl() {
        return this.getTitre().replace(" ", "-");
    }
}
