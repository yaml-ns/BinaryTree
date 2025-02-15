package controller;

import model.ArbreAVL;
import model.Livre;
import util.CSVReader;
import util.ESUtilisateur;
import view.LivreView;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class LivreController {
    private List<Livre> livres;
    private ArbreAVL<Livre> arbreCategorie;
    private ArbreAVL<Livre> arbreAuteur;
    private ArbreAVL<Livre> arbreTitre;
    private ArbreAVL<Livre> arbreDate;
    private ArbreAVL<Livre> arbrePrix;
    private LivreView view;

    public LivreController(String cheminFichier, LivreView view) throws IOException {
        this.view = view;
        livres = CSVReader.lireLivres(cheminFichier);

        arbreCategorie = new ArbreAVL<>(Comparator.comparing(Livre::getCategorie));
        arbreAuteur = new ArbreAVL<>(Comparator.comparing(Livre::getAuteur));
        arbreTitre = new ArbreAVL<>(Comparator.comparing(Livre::getTitre));
        arbreDate = new ArbreAVL<>(Comparator.comparing(Livre::getDatePublication).reversed());
        arbrePrix = new ArbreAVL<>(Comparator.comparing(Livre::getPrix));

        for (Livre livre : livres) {
            arbreCategorie.insererNode(livre);
            arbreAuteur.insererNode(livre);
            arbreTitre.insererNode(livre);
            arbreDate.insererNode(livre);
            arbrePrix.insererNode(livre);
        }
    }

    public void afficherParCategorie() {
        String categorie = ESUtilisateur.getText("Quelle catégorie ? : ");
        List<Livre> result = arbreCategorie.rechercherParCritere(Livre::getCategorie, categorie);
        view.afficherLivres(result);
    }

    public void afficherParAuteur() {
        String auteur = ESUtilisateur.getText("Quel est le nom de l'auteur ? : ");
        List<Livre> result = arbreAuteur.rechercherParCritere(Livre::getAuteur, auteur);
        view.afficherLivres(result);
    }

    public void afficherParTitre() {
        String titre = ESUtilisateur.getText("Quel est le titre du livre ? : ");
        List<Livre> result = arbreTitre.rechercherParCritere(Livre::getTitre, titre);
        view.afficherLivres(result);
    }

    public void afficherParDatePublication() {
        LocalDate datePublication = ESUtilisateur.getDate("Entrez la date de publication (yyyy-MM-dd): ");
        List<Livre> result = arbreDate.rechercherParCritere(Livre::getDatePublication, datePublication);
        view.afficherLivres(result);
    }

    public void afficherParPrixExact() {
        double prix = ESUtilisateur.getDouble(0,Double.MAX_VALUE,"Quel est le prix ? : ");
        List<Livre> result = arbrePrix.rechercherParCritere(Livre::getPrix, prix);
        view.afficherLivres(result);
    }

    public void afficherParCategorieEtPrix() {

        String categorie = ESUtilisateur.getText("Entrez la catégorie: ");
        double prixMin = ESUtilisateur.getDouble(0,Double.MAX_VALUE,"Entrez le prix minimum: ");
        double prixMax = ESUtilisateur.getDouble(0,Double.MAX_VALUE,"Entrez le prix minimum: ");

        List<Livre> result = new ArrayList<>();
        for (Livre livre : arbrePrix.parcoursInOrdre()) {
            if (livre.getCategorie().equalsIgnoreCase(categorie) &&
                    livre.getPrix() >= prixMin && livre.getPrix() <= prixMax) {
                result.add(livre);
            }
        }
        view.afficherLivres(result);
    }

    public void afficherLivresRuptureStock() {
        List<Livre> ruptureStock = new ArrayList<>();
        for (Livre livre : arbrePrix.parcoursInOrdre()) {
            if (livre.getQuantite() == 0) {
                ruptureStock.add(livre);
            }
        }
        view.afficherLivres(ruptureStock);
    }

    public void afficherParAuteurEtTitre() {
        String auteur = ESUtilisateur.getText("Entrez l'auteur: ");
        String debutTitre = ESUtilisateur.getText("Entrez les lettres de début du titre: ");

        List<Livre> result = new ArrayList<>();
        for (Livre livre : arbreAuteur.rechercherParCritere(Livre::getAuteur, auteur)) {
            if (livre.getTitre().toLowerCase().startsWith(debutTitre.toLowerCase())) {
                result.add(livre);
            }
        }
        result.sort(Comparator.comparing(Livre::getTitre));
        view.afficherLivres(result);
    }

    public void afficherParAuteurEtDate() {

        String auteur = ESUtilisateur.getText("Entrez l'auteur: ");
        LocalDate datePublication = ESUtilisateur.getDate("Quelle date de publication ? :");

        List<Livre> result = new ArrayList<>();
        for (Livre livre : arbreAuteur.rechercherParCritere(Livre::getAuteur, auteur)) {
            if (livre.getDatePublication().equals(datePublication)) {
                result.add(livre);
            }
        }
        view.afficherLivres(result);
    }

    public void afficherLivresHierarchie() {
        Map<String, Map<String, List<String>>> hierarchie = new TreeMap<>();

        for (Livre livre : livres) {
            hierarchie
                    .computeIfAbsent(livre.getCategorie(), k -> new TreeMap<>())
                    .computeIfAbsent(livre.getAuteur(), k -> new ArrayList<>())
                    .add(livre.getTitre());
        }

        hierarchie.values().forEach(auteurs ->
                auteurs.values().forEach(titres ->
                        titres.sort(Comparator.naturalOrder())
                )
        );

        view.afficherLivresHierarchie(hierarchie);
    }

    public void start() {
        while (true) {
            view.afficherMenu();
            int choix = ESUtilisateur.getInt(1,11,"Choisissez une option : ");

            switch (choix) {
                case 1:
                    afficherParCategorie();
                    break;
                case 2:

                    afficherParAuteur();
                    break;
                case 3:

                    afficherParTitre();
                    break;
                case 4:
                    afficherParDatePublication();
                    break;
                case 5:
                    afficherParPrixExact();
                    break;
                case 6:
                    afficherLivresRuptureStock();
                    break;
                case 7:
                     afficherParAuteurEtTitre();
                    break;
                case 8:
                    afficherParAuteurEtDate();
                    break;
                case 9:
                   afficherParCategorieEtPrix();
                    break;
                case 10:
                    afficherLivresHierarchie();
                    break;
                case 11:
                    System.out.println("Au revoir!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        }
    }
}
