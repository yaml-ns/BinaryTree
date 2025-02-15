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
            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] details = ligne.split(",");
                LocalDate datePublication = LocalDate.parse(details[5], formatter);
                Livre livre = new Livre(details[0], details[1], details[2], details[3], Double.parseDouble(details[4]), datePublication, Integer.parseInt(details[6]));
                livres.add(livre);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return livres;
    }
}
