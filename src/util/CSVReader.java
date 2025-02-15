package util;

import model.Livre;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public static List<Livre> lireLivres(String cheminFichier) throws IOException {
        List<Livre> livres = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne = br.readLine();
            while ((ligne = br.readLine()) != null) {
                String[] details = ligne.split(";");
                Livre livre = getLivre(details, formatter);
                livres.add(livre);
            }
        }
        return livres;
    }

    private static Livre getLivre(String[] details, DateTimeFormatter formatter) {
        String ISBN = details[0].replace("{", "").replace("}", "").trim();
        String titre = details[1].replace("{", "").replace("}", "").trim();
        String auteur = details[2].replace("{", "").replace("}", "").trim();
        String categorie = details[3].replace("{", "").replace("}", "").trim();
        double prix = Double.parseDouble(details[4].replace("{", "").replace("}", "").trim());
        LocalDate datePublication = LocalDate.parse(details[5].replace("{", "").replace("}", "").trim(), formatter);
        int quantite = Integer.parseInt(details[6].replace("{", "").replace("}", "").trim());

        Livre livre = new Livre(ISBN, titre, auteur, categorie, prix, datePublication, quantite);
        return livre;
    }
}
