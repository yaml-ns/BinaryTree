
import controller.LivreController;
import util.ESUtilisateur;
import view.LivreView;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            LivreView view = new LivreView();
            LivreController controller = new LivreController("src/data/livres.csv", view);
            Scanner scanner = new Scanner(System.in).useLocale(Locale.ENGLISH);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while (true) {
                view.afficherMenu();

                int choix = ESUtilisateur.getInt(1,11,"Choisissez une option : ");

                switch (choix) {
                    case 1:
                        String categorie = ESUtilisateur.getText("Quelle catégorie ? : ");
                        controller.afficherParCategorie(categorie);
                        break;
                    case 2:

                        String auteur = ESUtilisateur.getText("Quel est le nom de l'auteur ? : ");
                        controller.afficherParAuteur(auteur);
                        break;
                    case 3:
                        String titre = ESUtilisateur.getText("Quel est le titre du livre ? : ");
                        controller.afficherParTitre(titre);
                        break;
                    case 4:
                        LocalDate datePublication = ESUtilisateur.getDate("Entrez la date de publication (yyyy-MM-dd): ");
                        controller.afficherParDatePublication(datePublication);
                        break;
                    case 5:
                        double prix = ESUtilisateur.getDouble(0,Double.MAX_VALUE,"Quel est le prix ? : ");
                        controller.afficherParPrixExact(prix);
                        break;
                    case 6:
                        controller.afficherLivresRuptureStock();
                        break;
                    case 7:
                        String auteurSpecifique = ESUtilisateur.getText("Entrez l'auteur: ");
                        String debutTitre = ESUtilisateur.getText("Entrez les lettres de début du titre: ");
                        controller.afficherParAuteurEtTitre(auteurSpecifique, debutTitre);
                        break;
                    case 8:
                        System.out.print("Entrez l'auteur: ");
                        String auteurPourDate = scanner.nextLine();
                        controller.afficherParAuteurEtDate(auteurPourDate);
                        break;
                    case 9:
                        String categoriePourPrix = ESUtilisateur.getText("Entrez la catégorie: ");
                        double prixCategorieMin = ESUtilisateur.getDouble(0,Double.MAX_VALUE,"Entrez le prix minimum: ");
                        double prixCategorieMax = ESUtilisateur.getDouble(0,Double.MAX_VALUE,"Entrez le prix minimum: ");
                        controller.afficherParCategorieEtPrix(categoriePourPrix, prixCategorieMin, prixCategorieMax);
                        break;
                    case 10:
                        controller.afficherLivresHierarchie();
                        break;
                    case 11:
                        System.out.println("Au revoir!");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Option invalide, veuillez réessayer.");
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }
}
