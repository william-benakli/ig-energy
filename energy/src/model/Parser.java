package model;

import model.typeenum.DirCarre;
import model.typeenum.DirHexa;
import model.typeenum.TuileComposant;
import model.typeenum.TuileShape;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    public Level level;

    public Level parseLineToLevel(String levelName) throws FileNotFoundException, ParseException {

        final File file = new File(levelName);
        final Scanner scanner = new Scanner(file);

        String firstLine = scanner.nextLine();
        String[] arrayLine = firstLine.split(" ");

        if (!(arrayLine[2].equalsIgnoreCase("S") || arrayLine[2].equalsIgnoreCase("H")))
            throw new ParseException("Erreur à la ligne", 0);

        TuileShape shape = arrayLine[2].equalsIgnoreCase("S") ? TuileShape.CARRE : TuileShape.HEXA;
        this.level = new Level(Integer.parseInt(arrayLine[0]), Integer.parseInt(arrayLine[1]), shape, levelName.replace(".nrg", "").split("/")[2]);

        int positionLine = 0;
        while (scanner.hasNextLine()) {
            final String line = scanner.nextLine();
            parseLine(line, ++positionLine, level);
        }

        scanner.close();
        return level;
    }

    private void parseLine(String line, int positionLine, Level level) throws ParseException {
        String[] arrayLine = line.split(" ");
        int compteurColonne = 0;

        TuileComposant currComposant = null;
        ArrayList<Integer> listEdge = new ArrayList<>();

        for (String s : arrayLine) {
            if ((s.charAt(0) >= 65 && s.charAt(0) <= 90) || s.charAt(0) == 46) {
                if (currComposant != null) addTuile(positionLine, level, compteurColonne++, currComposant, listEdge);
                switch (s) {
                    case "." -> currComposant = TuileComposant.EMPTY;
                    case "S" -> currComposant = TuileComposant.ENERGY;
                    case "L" -> currComposant = TuileComposant.LIGHT;
                    case "W" -> currComposant = TuileComposant.WIFI;
                    default -> throw new ParseException("Erreur à la ligne", positionLine);
                }
            } else if (s.charAt(0) >= 48 && s.charAt(0) <= 57) listEdge.add(Integer.parseInt(s));
            else throw new ParseException("Erreur à la ligne", positionLine);
        }

        if (currComposant != null)
            addTuile(positionLine, level, compteurColonne, currComposant, listEdge);
    }

    private void addTuile(int positionLine, Level level, int compteurColonne, TuileComposant currComposant, ArrayList<Integer> listEdge) {
        Tuile tuile =  new Tuile(level.getTypeTuilePlateau(), currComposant, (level.getTypeTuilePlateau() == TuileShape.CARRE? DirCarre.NORD : DirHexa.NORD), new Position((positionLine - 1), compteurColonne));
        level.setTuileAt((positionLine - 1), compteurColonne, tuile);
        for (Integer i : listEdge) tuile.setEdgeBoolean(i, true);
        tuile.update();
        listEdge.clear();
    }

}
