package util;

import model.Livre;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public static List<Livre> lireLivres(String cheminFichier) throws IOException {
        List<Livre> livres = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] details = ligne.split(";");
                Livre livre = new Livre(details[0], details[1], details[2], details[3], Double.parseDouble(details[4]), details[5], Integer.parseInt(details[6]));
                livres.add(livre);
            }
        }
        return livres;
    }
}
