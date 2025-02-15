package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Classe utilitaire pour les interactions avec l'utilisateur.
 */
public class ESUtilisateur {

    /**
     * Affiche un message à l'utilisateur.
     *
     * @param message Le message à afficher.
     */
    public static void afficherMessage(String message){
        System.out.println(message);
    }

    /**
     * Demande un texte à l'utilisateur avec un message.
     *
     * @param message Le message demandant un texte à l'utilisateur.
     * @return Le texte saisi par l'utilisateur.
     */
    public static String getText(String message){
        String text;
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        while (true) {
            if (scanner.hasNextLine()) {
                text = scanner.nextLine();
                if (!text.isBlank()) {
                    break;
                } else {
                    System.out.println("Veuillez saisir une chaine valide");
                }
            } else {
                System.out.println("Veuillez saisir une chaine valide !");
                scanner.next();
            }
        }
        return text;
    }

    /**
     * Demande un entier à l'utilisateur dans un intervalle spécifique avec un message.
     *
     * @param min Le minimum acceptable.
     * @param max Le maximum acceptable.
     * @param message Le message demandant un entier à l'utilisateur.
     * @return Le nombre entier saisi par l'utilisateur.
     */
    public static int getInt(int min, int max, String message) {
        int nombre;
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        while (true) {
            if (scanner.hasNextInt()) {
                nombre = scanner.nextInt();
                if (nombre >= min && nombre <= max) {
                    break;
                } else {
                    System.out.println("Veuillez saisir un entier entre " + min + " et " + max + " ");
                }
            } else {
                System.out.println("Veuillez saisir un entier valide !");
                scanner.next();
            }
        }
        return nombre;
    }

    /**
     * Demande un nombre décimal à l'utilisateur dans un intervalle spécifique avec un message.
     *
     * @param min Le minimum acceptable.
     * @param max Le maximum acceptable.
     * @param message Le message demandant un nombre décimal à l'utilisateur.
     * @return Le nombre décimal saisi par l'utilisateur.
     */
    public static double getDouble(double min, double max, String message) {
        double nombre;
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        while (true) {
            if (scanner.hasNextDouble()) {
                nombre = scanner.nextDouble();
                if (nombre >= min && nombre <= max) {
                    break;
                } else {
                    System.out.println("Veuillez saisir un décimal entre " + min + " et " + max + " ");
                }
            } else {
                System.out.println("Veuillez saisir un décimal valide !");
                scanner.next();
            }
        }
        return nombre;
    }

    /**
     * Demande une confirmation (oui ou non) à l'utilisateur avec un message.
     *
     * @param message Le message demandant une confirmation à l'utilisateur.
     * @return true si l'utilisateur répond "oui" ou "o", false sinon.
     */
    public static boolean confirm(String message) {
        boolean response;
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        while (true) {
            if (scanner.hasNextLine()) {
                String textResponse = scanner.nextLine();
                response = textResponse.equalsIgnoreCase("oui") || textResponse.equalsIgnoreCase("o");
                break;
            } else {
                System.out.println("Veuillez saisir une réponse valide svp !");
                scanner.next();
            }
        }
        return response;
    }

    /**
     * Demande une date à l'utilisateur avec un message.
     *
     * @param message Le message demandant une date à l'utilisateur.
     * @return La date saisie par l'utilisateur.
     */
    public static LocalDate getDate(String message) {
        LocalDate date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        while (true) {
            if (scanner.hasNextLine()) {
                String stringDate = scanner.nextLine();
                try {
                    date =  LocalDate.parse(stringDate, formatter);
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("Veuillez saisir une date valide au format yyyy-MM-dd");
                }
            } else {
                System.out.println("Veuillez saisir une réponse valide svp !");
                scanner.next();
            }
        }
        return date;
    }
}
