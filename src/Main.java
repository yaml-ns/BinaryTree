
import controller.LivreController;
import util.ESUtilisateur;
import view.LivreView;

import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        try {
            LivreView view = new LivreView();
            LivreController controller = new LivreController("src/data/livres.csv", view);
            controller.start();

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }
}
