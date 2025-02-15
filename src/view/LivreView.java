package view;

import model.Livre;

import java.util.List;

public class LivreView {
    public void afficherLivres(List<Livre> livres) {
        for (Livre livre : livres) {
            System.out.println(livre);
        }
    }
}
