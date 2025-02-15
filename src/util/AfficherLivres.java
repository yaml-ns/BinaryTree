package util;

import model.Livre;

import java.util.List;

public class AfficherLivres {

    private static int isbn = 25;
    private static int titre=35;
    private static int auteur = 25;
    private static int prix = 10;
    private static int categorie = 20;
    private static int date = 20;
    private static int quantite = 10;
    public static void afficher(List<Livre> livres){
        imprimerHeader();
        for (Livre livre : livres){
            imprimerLigne(livre);
        }
    }

    private static void imprimerBordureColonne(char c, int loop){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < loop; i++) {
            builder.append(c);
        }
        builder.append("+");
        System.out.print(builder);
    }
    private static void imprimerColonne(String nom, int loop){
        StringBuilder builder = new StringBuilder();
        builder.append(nom);
        int espaces = loop - nom.length();
        builder.append(" ".repeat(Math.max(0, espaces)));
        builder.append("|");
        System.out.print(builder);
    }
    private static void imprimerSeparateurLigne(){
        imprimerBordureColonne('-',isbn);
        imprimerBordureColonne('-',titre);
        imprimerBordureColonne('-',auteur);
        imprimerBordureColonne('-',categorie);
        imprimerBordureColonne('-',prix);
        imprimerBordureColonne('-',date);
        imprimerBordureColonne('-',quantite);
        System.out.println();
    }
    private static void imprimerNomsHeader(){
        imprimerColonne("ISBN",isbn);
        imprimerColonne("Titre",titre);
        imprimerColonne("Auteur",auteur);
        imprimerColonne("Catégorie",categorie);
        imprimerColonne("Prix",prix);
        imprimerColonne("Date de publication",date);
        imprimerColonne("Qte",date);
        System.out.println();
    }

    private static void imprimerLigne(Livre livre){
        imprimerColonne(livre.getISBN(),isbn);
        imprimerColonne(livre.getTitre(),titre);
        imprimerColonne(livre.getAuteur(),auteur);
        imprimerColonne(livre.getCategorie(),categorie);
        imprimerColonne(String.valueOf(livre.getPrix()),prix);
        imprimerColonne(livre.getDatePublication().toString(),date);
        imprimerColonne(String.valueOf(livre.getQuantite()),quantite);
        System.out.println();
        imprimerSeparateurLigne();
    }

    private static void imprimerHeader(){
        imprimerSeparateurLigne();
        imprimerNomsHeader();
        imprimerSeparateurLigne();
    }
}