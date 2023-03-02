import model.Level;
import model.Tuile;
import model.TuileCarre;
import model.TuileVide;
import model.typeenum.ImageEnum;
import model.typeenum.TuileComposant;
import vue.FenetreJFrame;

import javax.swing.*;
import java.io.File;
import java.text.ParseException;
import java.util.Scanner;

public class MainEnergy {


    public static void main(String[] args) {


        Level niveau_1 = null;
        try {
            niveau_1 = parseLineToLevel("ressource/level/level1.nrg");
        } catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable.");
        }

        new FenetreJFrame(niveau_1);

    }


    static Level parseLineToLevel(String levelName) throws FileNotFoundException {
       // int niveauLevel = Integer.parseInt(levelName.replace("level", "").replace(".nrg", "").toLowerCase().trim());

        final File file = new File(levelName);
        final Scanner scanner = new Scanner(file);

        String firstLine = scanner.nextLine();
        String arrayLine[] = firstLine.split(" ");

        System.out.println(Arrays.toString(arrayLine));
        System.out.println(arrayLine[0]);
        System.out.println(arrayLine[1]);

        Level level = new Level(0, Integer.parseInt(arrayLine[0]), Integer.parseInt(arrayLine[1]));

        try {
            int positionLine = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                parseLine(line, ++positionLine, level);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        scanner.close();
        return level;
    }

    private static void parseLine(String line, int positionLine, Level level) throws ParseException {
        String[] arrayLine = line.split(" ");
        for (String s : arrayLine) {
           if ((s.charAt(0) >= 65 && s.charAt(0) <= 90) || s.charAt(0) == 46) {
                //TODO: Contruire un nouvel objet
               System.out.println(s + "avant switch(s)");
               switch (s) {
                   case "." -> level.addTuile((positionLine - 1), new TuileVide());
                   case "S" -> {
                       System.out.println("ENERGY");
                       level.addTuile((positionLine - 1), new TuileCarre(TuileComposant.ENERGY));
                   }
                   case "L" -> {
                       System.out.println("LAMP");
                       level.addTuile((positionLine - 1), new TuileCarre(TuileComposant.LIGHT));
                   }
                   case "W" -> {
                       System.out.println("WIFI");
                       level.addTuile((positionLine - 1), new TuileCarre(TuileComposant.WIFI));
                   }
                   default -> {
                       System.out.println(s + " croisé");
                       throw new ParseException("Erreur à la ligne", positionLine);
                   }
               }

            } else if (s.charAt(0) >= 48 && s.charAt(0) <= 57) {
                //TODO: Ajouter des liens à un objet
            } else throw new ParseException("Erreur à la ligne", positionLine);
        }

        if(currComposant!=null){
            level.addTuile((positionLine - 1), new TuileCarre(currComposant, new ArrayList<>(listConnexion)));
            listConnexion.clear();
        }
    }

}
