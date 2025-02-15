package model;

public class Livre {
    private String ISBN;
    private String titre;
    private String auteur;
    private String categorie;
    private double prix;
    private String datePublication;
    private int quantite;

    public Livre(String ISBN, String titre, String auteur, String categorie, double prix, String datePublication, int quantite) {
        this.ISBN = ISBN;
        this.titre = titre;
        this.auteur = auteur;
        this.categorie = categorie;
        this.prix = prix;
        this.datePublication = datePublication;
        this.quantite = quantite;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "ISBN='" + ISBN + '\'' +
                ", titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", categorie='" + categorie + '\'' +
                ", prix=" + prix +
                ", datePublication='" + datePublication + '\'' +
                ", quantite=" + quantite +
                '}';
    }
}
