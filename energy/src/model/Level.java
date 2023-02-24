package model;

import model.typeenum.DirHexa;
import model.typeenum.TuileComposant;

import java.util.ArrayList;

public final class Level {

    private ArrayList<ArrayList<Tuile>> plateau;
    private int idLevel;
    private Joueur j;
    private int weight, height;

    public Level(int idLevel, int weight, int height){
        this.plateau = new ArrayList<>();
        this.idLevel = idLevel;
        this.weight = weight;
        this.height = height;

        for (int i = 0; i < weight ; i++) {
            ArrayList<Tuile> verticalTuile = new ArrayList<>();
            for (int j = 0; j < height; j++) {
               // verticalTuile.add(new Tuile(DirHexa.AUCUN, x   ));
            }
            plateau.add(verticalTuile);
        }
    }

    public void setTuileAt(int x, int y, Tuile tuile){
        this.plateau.get(x).set(y, tuile);
    }
    public int getWeight() {
        return weight;
    }
    public int getHeight(){
        return height;
    }
    public ArrayList<ArrayList<Tuile>> getPlateau() {
        return this.plateau;
    }
}
