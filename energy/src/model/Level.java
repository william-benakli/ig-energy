package model;

import java.util.ArrayList;

public final class Level {

    private ArrayList<ArrayList<Tuile>> plateau;
    private int idLevel;
    private Joueur j;

    public Level(int idLevel){
        this.plateau = new ArrayList<ArrayList<Tuile>>();
        this.idLevel = idLevel;
    }

}
