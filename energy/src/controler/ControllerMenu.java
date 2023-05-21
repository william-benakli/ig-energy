package controler;

import model.Level;
import model.Parser;
import vue.level.BoxLevelJPanel;

import java.io.File;
import java.io.FileNotFoundException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;

public class ControllerMenu {

    public final ArrayList<Level> listLvl = new ArrayList<>();

   public ControllerMenu(){
       try {
            chargeLevel("ressource/level");
        } catch (
                FileNotFoundException e) {
            System.out.println("impossible de charger les niveaux.");
        }
    }

    public void chargeLevel(String cheminLevel) throws FileNotFoundException {
        File dir = new File(cheminLevel);
        if (!dir.exists()) throw new FileNotFoundException();
        File[] liste = dir.listFiles();
        for (File item : liste) {
            if (item.isFile()) {
                Level level = null;
                try {
                    level = new Parser().parseLineToLevel(cheminLevel + "/" + item.getName());
                    listLvl.add(level);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        listLvl.sort(Comparator.comparingInt(chiffre -> Integer.parseInt(chiffre.getNameLevel().replace("level", ""))));
    }
}
