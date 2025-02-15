package controller;

import model.ArbreAVL;
import model.Livre;
import util.CSVReader;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class LivreController {
    private List<Livre> livres;
    private ArbreAVL<Livre> arbreCategorie;
    private ArbreAVL<Livre> arbreAuteur;
    private ArbreAVL<Livre> arbreTitre;
    private ArbreAVL<Livre> arbrePrix;
    private ArbreAVL<Livre> arbreDate;

    public LivreController(String cheminFichier) throws IOException {
        livres = CSVReader.lireLivres(cheminFichier);

        arbreCategorie = new ArbreAVL<>(Comparator.comparing(Livre::getCategorie));
        arbreAuteur = new ArbreAVL<>(Comparator.comparing(Livre::getAuteur));
        arbreTitre = new ArbreAVL<>(Comparator.comparing(Livre::getTitre));
        arbrePrix = new ArbreAVL<>(Comparator.comparing(Livre::getPrix));
        arbreDate = new ArbreAVL<>(Comparator.comparing(Livre::getDatePublication).reversed());

        for (Livre livre : livres) {
            arbreCategorie.insererNode(livre);
            arbreAuteur.insererNode(livre);
            arbreTitre.insererNode(livre);
            arbreDate.insererNode(livre);
        }
    }

    public List<Livre> chercherParCategorie(String categorie) {
        return filtreEtTrie(arbreCategorie, categorie);
    }

    public List<Livre> chercherParAuteur(String auteur) {
        return filtreEtTrie(arbreAuteur, auteur);
    }

    public List<Livre> chercherParTitre(String titre) {
        return filtreEtTrie(arbreTitre, titre);
    }

    public List<Livre> chercherParDatePublication(LocalDate datePublication) {
        return filtreEtTrie(arbreDate, datePublication);
    }

    public List<Livre> chercherParPrixExact(double prix) {
        ArbreAVL<Livre> arbrePrix = new ArbreAVL<>(Comparator.comparing(Livre::getPrix));
        for (Livre livre : livres) {
            if (livre.getPrix() == prix) {
                arbrePrix.insererNode(livre);
            }
        }
        return arbrePrix.parcoursInOrdre();
    }

    public List<Livre> chercherParCategorieEtPrix(String categorie, double prixMin, double prixMax) {
        List<Livre> result = new ArrayList<>();
        for (Livre livre : arbrePrix.parcoursInOrdre()) {
            if (livre.getCategorie().equalsIgnoreCase(categorie) &&
                    livre.getPrix() >= prixMin && livre.getPrix() <= prixMax) {
                result.add(livre);
            }
        }
        return result;
    }

    public List<Livre> chercherLivresRuptureStock() {
        List<Livre> ruptureStock = new ArrayList<>();
        for (Livre livre : livres) {
            if (livre.getQuantite() == 0) {
                ruptureStock.add(livre);
            }
        }
        return ruptureStock;
    }

    public List<Livre> chercherParAuteurEtTitre(String auteur, String debutTitre) {
        List<Livre> result = new ArrayList<>();
        for (Livre livre : arbreAuteur.parcoursInOrdre()) {
            if (livre.getAuteur().equalsIgnoreCase(auteur) &&
                    livre.getTitre().toLowerCase().startsWith(debutTitre.toLowerCase())) {
                result.add(livre);
            }
        }
        result.sort(Comparator.comparing(Livre::getTitre));
        return result;
    }

    public List<Livre> chercherParAuteurEtDate(String auteur) {
        List<Livre> result = new ArrayList<>();
        for (Livre livre : arbreDate.parcoursInOrdre()) {
            if (livre.getAuteur().equalsIgnoreCase(auteur)) {
                result.add(livre);
            }
        }
        return result;
    }

        private List<Livre> filtreEtTrie(ArbreAVL<Livre> arbre, Object critere) {
        List<Livre> result = new ArrayList<>();
        for (Livre livre : arbre.parcoursInOrdre()) {
            if (critere.equals(livre.getCategorie()) || critere.equals(livre.getAuteur()) || critere.equals(livre.getTitre()) || critere.equals(livre.getDatePublication())) {
                result.add(livre);
            }
        }
        return result;
    }

    public Map<String, Map<String, List<String>>> afficherLivresHiérarchie() {
        Map<String, Map<String, List<String>>> hierarchy = new TreeMap<>();

        for (Livre livre : livres) {
            hierarchy
                    .computeIfAbsent(livre.getCategorie(), k -> new TreeMap<>())
                    .computeIfAbsent(livre.getAuteur(), k -> new ArrayList<>())
                    .add(livre.getTitre());
        }

        hierarchy.values().forEach(auteurs ->
                auteurs.values().forEach(titres ->
                        titres.sort(Comparator.naturalOrder())
                )
        );

        return hierarchy;
    }
}
