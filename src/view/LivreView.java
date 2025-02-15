package view;

import model.Livre;
import util.AfficherLivres;

import java.util.List;
import java.util.Map;

public class LivreView {

    public void afficherMenu(){
        System.out.println("""
            *** MENU ***
        ------------------------------------------------------------------------------------------
         1. Afficher les livres par Catégorie
         2. Afficher les livres par Auteur
         3. Afficher les livres par Titre
         4. Afficher les livres par Date de publication
         5. Afficher les livres par Prix
         6. Afficher les livres en rupture de stock
        -------------------------------------------------------------------------------------------
         7. Trouver les livres d'un auteur spécifique dont le titre commence par des lettres données
         8. Trouver les livres d'un auteur spécifique selon la date de publication
         9. Trouver les livres d'une catégorie dans un intervalle de prix
        10. Afficher la hiérarchie des livres
        --------------------------------------------------------------------------------------------
        11. Quitter
        --------------------------------------------------------------------------------------------
        """);
    }

    public void afficherLivres(List<Livre> livres) {
        AfficherLivres.afficher(livres);
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
