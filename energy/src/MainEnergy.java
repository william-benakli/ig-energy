import model.Tuile;
import model.typeenum.ImageEnum;
import vue.FenetreJFrame;

import javax.swing.*;
import java.io.File;
import java.text.ParseException;
import java.util.Scanner;

public class MainEnergy {

    static Scanner scanner = new Scanner("level1.nrg");

    public static void main(String[] args) {

        getDimensionMap();
        readLines();
    }


    static void getDimensionMap() {
        String firstLine = scanner.nextLine();
        String arrayLine[] = firstLine.split(" ");
        //TODO: dimensions de la map
    }

    static void readLines() {

        try {
            int positionLine = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                parseLine(line, ++positionLine);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        scanner.close();
    }

    private static void parseLine(String line, int positionLine) throws ParseException {
        String[] arrayLine = line.split(" ");
        for (String s : arrayLine) {
            if (s.equals(".")) {
                //TODO: ajouter un objet vide
            } else if (s.charAt(0) >= 65 || s.charAt(0) <= 90 || s.charAt(0) == 46) {
                //TODO: Contruire un nouvel objet
                switch(s){
                    case ".":
                        //new Tuile(ImageEnum.SQUARE_OFF_EMPTY);
                        break;
                    case "S":
                        //new Tuile(ImageEnum.SQUARE_ON_COMPOSANT_ENERGY);
                        break;
                    case "L":
                        //new Tuile(ImageEnum.SQUARE_OFF_COMPOSANT_LAMP);
                        break;
                    case "W":
                        //new Tuile(ImageEnum.SQUARE_OFF_COMPOSANT_WIFI);
                        break;
                    default:
                        throw new ParseException("Erreur à la ligne", positionLine);
                }
            } else if (s.charAt(0) >= 48 || s.charAt(0) <= 57) {
                //TODO: Ajouter des liens à un objet
            } else throw new ParseException("Erreur à la ligne", positionLine);
        }
    }

}
