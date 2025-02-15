package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ESUtilisateur {

    public static void afficherMessage(String message){
        System.out.println(message);
    }

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
