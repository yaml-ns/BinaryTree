import controller.LivreController;
import model.Livre;
import view.LivreView;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            LivreController controller = new LivreController("src/data/livres.csv");
            LivreView view = new LivreView();
            Scanner scanner = new Scanner(System.in).useLocale(Locale.ENGLISH);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while (true) {
                System.out.println("Menu:");
                System.out.println(" 1. Afficher les livres par Catégorie");
                System.out.println(" 2. Afficher les livres par Auteur");
                System.out.println(" 3. Afficher les livres par Titre");
                System.out.println(" 4. Afficher les livres par Date de publication");
                System.out.println(" 5. Afficher les livres par Prix");
                System.out.println(" 6. Afficher les livres par catégorie > Auteur > Titre");
                System.out.println(" 7. Afficher les livres en rupture de stock");
                System.out.println(" 8. Trouver les livres d'un auteur spécifique dont le titre commence par des lettres données");
                System.out.println(" 9. Trouver les livres d'un auteur spécifique selon la date de publication");
                System.out.println("10. Trouver les livres d'une catégorie dans un intervalle de prix");
                System.out.println("11. Quitter");
                System.out.print("Choisissez une option: ");

                int choix = scanner.nextInt();
                scanner.nextLine();



                switch (choix) {
                    case 1:
                        System.out.print("Entrez la catégorie: ");
                        String categorie = scanner.nextLine();
                        List<Livre> livresParCategorie = controller.chercherParCategorie(categorie);
                        view.afficherLivres(livresParCategorie);
                        break;
                    case 2:
                        System.out.print("Entrez l'auteur: ");
                        String auteur = scanner.nextLine();
                        List<Livre> livresParAuteur = controller.chercherParAuteur(auteur);
                        view.afficherLivres(livresParAuteur);
                        break;
                    case 3:
                        System.out.print("Entrez le titre: ");
                        String titre = scanner.nextLine();
                        List<Livre> livresParTitre = controller.chercherParTitre(titre);
                        view.afficherLivres(livresParTitre);
                        break;
                    case 4:
                        System.out.print("Entrez la date de publication (yyyy-MM-dd): ");
                        LocalDate datePublication = LocalDate.parse(scanner.nextLine(), formatter);
                        List<Livre> livresParDate = controller.chercherParDatePublication(datePublication);
                        view.afficherLivres(livresParDate);
                        break;
                    case 5:
                        System.out.print("Entrez le prix: ");
                        while (!scanner.hasNextDouble()) {
                            System.out.println("Veuillez entrer un nombre valide.");
                            scanner.next();
                        }
                        double prix = scanner.nextDouble();
                        List<Livre> livresParPrixExact = controller.chercherParPrixExact(prix);
                        view.afficherLivres(livresParPrixExact);
                        break;
                    case 6:
                        Map<String, Map<String, List<String>>> hierarchy = controller.afficherLivresHiérarchie();
                        view.afficherLivresHierarchie(hierarchy);
                        break;
                    case 7:
                        List<Livre> livresRuptureStock = controller.chercherLivresRuptureStock();
                        view.afficherLivres(livresRuptureStock);
                        break;
                    case 8:
                        System.out.print("Entrez l'auteur: ");
                        String auteurSpecifique = scanner.nextLine();
                        System.out.print("Entrez les lettres de début du titre: ");
                        String debutTitre = scanner.nextLine();
                        List<Livre> livresParAuteurEtTitre = controller.chercherParAuteurEtTitre(auteurSpecifique, debutTitre);
                        view.afficherLivres(livresParAuteurEtTitre);
                        break;
                    case 9:
                        System.out.print("Entrez l'auteur: ");
                        String auteurPourDate = scanner.nextLine();
                        List<Livre> livresParAuteurEtDate = controller.chercherParAuteurEtDate(auteurPourDate);
                        view.afficherLivres(livresParAuteurEtDate);
                        break;
                    case 10:
                        System.out.print("Entrez la catégorie: ");
                        String categoriePourPrix = scanner.nextLine();
                        System.out.print("Entrez le prix minimum: ");
                        while (!scanner.hasNextDouble()) {
                            System.out.println("Veuillez entrer un nombre valide.");
                            scanner.next();
                        }
                        double prixCategorieMin = scanner.nextDouble();
                        System.out.print("Entrez le prix maximum: ");
                        while (!scanner.hasNextDouble()) {
                            System.out.println("Veuillez entrer un nombre valide.");
                            scanner.next();
                        }
                        double prixCategorieMax = scanner.nextDouble();
                        List<Livre> livresParCategorieEtPrix = controller.chercherParCategorieEtPrix(categoriePourPrix, prixCategorieMin, prixCategorieMax);
                        view.afficherLivres(livresParCategorieEtPrix);
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
