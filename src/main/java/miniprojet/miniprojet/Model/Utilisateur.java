package miniprojet.miniprojet.Model;
import javax.persistence.*;

@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser", nullable = false)
    private int iduser;

    @Column(name="mail")
    private String mail;

    public int getIdAuteur() {
        return iduser;
    }

    public void setIdAuteur(int idAuteur) {
        this.iduser = idAuteur;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Column(name ="mdp")
    private String mdp;
}
