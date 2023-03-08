package model;


import model.typeenum.TuileShape;

import java.util.ArrayList;

public final class Level {

    private Tuile[][] plateau;
    private int idLevel;
    private Joueur j;
    private int weight, height;
    private TuileShape typeTuilePlateau;

    public Level(int idLevel, int weight, int height, TuileShape typeTuilePlateau){
        this.plateau = new Tuile[height][weight];
        this.idLevel = idLevel;
        this.weight = weight;
        this.height = height;
        this.typeTuilePlateau = typeTuilePlateau;
    }

    public void setTuileAt(int i, int j, Tuile tuile){
        this.plateau[i][j] = tuile;
    }
    public int getWeight() {
        return weight;
    }
    public int getHeight(){
        return height;
    }
    public Tuile[][] getPlateau() {
        return this.plateau;
    }
    public TuileShape getTypeTuilePlateau() {return typeTuilePlateau;}
}
