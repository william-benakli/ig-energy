package model;

import model.typeenum.TuileComposant;
import model.typeenum.TuileShape;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

public class Composer {

    public Composer(String name, Level level) {

        try {
            saveInFile(name, composeLine(level));
        } catch (IOException | ParseException e) {
            System.out.println("Echec de la sauvegarde");
        }

    }

    public static void saveInFile(String filename, String[] arr) throws IOException {

        FileWriter file = new FileWriter("ressource/level/perso/" +filename+".nrg");

        BufferedWriter ow = new BufferedWriter(file);
        for (String s : arr) {
            ow.write(s);
            ow.newLine();
        }
        ow.flush();
        ow.close();
    }

    private String[] composeLine(Level level) throws ParseException {
        String[] result = new String[level.getHeight() + 1];

        result[0] = level.getHeight() + " " + level.getWidth() + " " + ((level.getTypeTuilePlateau() == TuileShape.CARRE) ? "S" : "H");

        for (int i = 1; i < level.getHeight()+1; i++) {
            result[i] = createLine(level, i-1);
        }
        return result;
    }

    private String createLine(Level level, int positionLine) throws ParseException {

        StringBuilder result = new StringBuilder();

        for (int j = 0; j < level.getWidth(); j++) {

            boolean[] arr = level.getPlateau()[positionLine][j].getEdge();
            TuileComposant tcp = level.getPlateau()[positionLine][j].getComposant();

            switch (tcp) {
                case WIFI -> result.append("W");
                case EMPTY -> result.append(".");
                case LIGHT -> result.append("L");
                case ENERGY -> result.append("S");
                default -> throw new ParseException("Erreur à la ligne", positionLine);
            }

            for (int i1 = 0; i1 < arr.length; i1++) {

                if (arr[i1])
                    result.append(" ").append(i1);

                if (i1 == arr.length - 1)
                    result.append(" ");

            }

        }
        return result.toString();
    }


}
