package controller;

import model.ArbreAVL;
import model.Livre;
import util.CSVReader;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LivreController {
    private List<Livre> livres;
    private ArbreAVL<Livre> arbreCategorie;
    private ArbreAVL<Livre> arbreAuteur;
    private ArbreAVL<Livre> arbreTitre;
    private ArbreAVL<Livre> arbreDate;

    public LivreController(String cheminFichier) throws IOException {
        livres = CSVReader.lireLivres(cheminFichier);

        arbreCategorie = new ArbreAVL<>(Comparator.comparing(Livre::getCategorie));
        arbreAuteur = new ArbreAVL<>(Comparator.comparing(Livre::getAuteur));
        arbreTitre = new ArbreAVL<>(Comparator.comparing(Livre::getTitre));
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
        for (Livre livre : livres) {
            if (livre.getCategorie().equalsIgnoreCase(categorie) &&
                    livre.getPrix() >= prixMin && livre.getPrix() <= prixMax) {
                result.add(livre);
            }
        }
        result.sort(Comparator.comparing(Livre::getPrix));
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
        ArbreAVL<Livre> arbreAuteurEtTitre = new ArbreAVL<>(Comparator.comparing(Livre::getTitre));
        for (Livre livre : livres) {
            if (livre.getAuteur().equalsIgnoreCase(auteur) &&
                    livre.getTitre().toLowerCase().startsWith(debutTitre.toLowerCase())) {
                arbreAuteurEtTitre.insererNode(livre);
            }
        }
        return arbreAuteurEtTitre.parcoursInOrdre();
    }

    public List<Livre> chercherParAuteurEtDate(String auteur) {
        ArbreAVL<Livre> arbreAuteurEtDate = new ArbreAVL<>(Comparator.comparing(Livre::getDatePublication).reversed());
        for (Livre livre : livres) {
            if (livre.getAuteur().equalsIgnoreCase(auteur)) {
                arbreAuteurEtDate.insererNode(livre);
            }
        }
        return arbreAuteurEtDate.parcoursInOrdre();
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
}
