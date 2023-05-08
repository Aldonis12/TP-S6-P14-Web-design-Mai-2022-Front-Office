package miniprojet.miniprojet.Model;

public class Actualite {
    String annonce;
    Article article;
    String dateCreation;

    public String getAnnonce() {
        return annonce;
    }

    public void setAnnonce(String annonce) {
        this.annonce = annonce;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }
}
