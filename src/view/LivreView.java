package view;

import model.Livre;

import java.util.List;
import java.util.Map;

public class LivreView {
    public void afficherLivres(List<Livre> livres) {
        for (Livre livre : livres) {
            System.out.println(livre);
        }
    }

    public void afficherLivresHierarchie(Map<String, Map<String, List<String>>> hierarchy) {
        hierarchy.forEach((categorie, auteurs) -> {
            System.out.println("Catégorie: " + categorie);
            auteurs.forEach((auteur, titres) -> {
                System.out.println("  Auteur: " + auteur);
                titres.forEach(titre -> System.out.println("    Titre: " + titre));
            });
        });
    }
}
