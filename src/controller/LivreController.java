package controller;

import model.ArbreAVL;
import model.Livre;
import util.CSVReader;
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

    public void afficherParCategorie(String categorie) {
        List<Livre> result = arbreCategorie.rechercherParCritere(Livre::getCategorie, categorie);
        view.afficherLivres(result);
    }

    public void afficherParAuteur(String auteur) {
        List<Livre> result = arbreAuteur.rechercherParCritere(Livre::getAuteur, auteur);
        view.afficherLivres(result);
    }

    public void afficherParTitre(String titre) {
        List<Livre> result = arbreTitre.rechercherParCritere(Livre::getTitre, titre);
        view.afficherLivres(result);
    }

    public void afficherParDatePublication(LocalDate datePublication) {
        List<Livre> result = arbreDate.rechercherParCritere(Livre::getDatePublication, datePublication);
        view.afficherLivres(result);
    }

    public void afficherParPrixExact(double prix) {
        List<Livre> result = arbrePrix.rechercherParCritere(Livre::getPrix, prix);
        view.afficherLivres(result);
    }

    public void afficherParCategorieEtPrix(String categorie, double prixMin, double prixMax) {
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

    public void afficherParAuteurEtTitre(String auteur, String debutTitre) {
        List<Livre> result = new ArrayList<>();
        for (Livre livre : arbreAuteur.rechercherParCritere(Livre::getAuteur, auteur)) {
            if (livre.getTitre().toLowerCase().startsWith(debutTitre.toLowerCase())) {
                result.add(livre);
            }
        }
        result.sort(Comparator.comparing(Livre::getTitre));
        view.afficherLivres(result);
    }

    public void afficherParAuteurEtDate(String auteur, LocalDate datePublication) {
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
}
