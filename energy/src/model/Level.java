package model;


import model.typeenum.TuileShape;

import java.util.ArrayList;

public final class Level {

    private Tuile[][] plateau;
    private int idLevel;
    private Joueur j;
    private int width, height;
    private TuileShape typeTuilePlateau;

    public Level(int idLevel, int height, int width, TuileShape typeTuilePlateau){
        this.plateau = new Tuile[height][width];
        this.idLevel = idLevel;
        this.width = width;
        this.height = height;
        this.typeTuilePlateau = typeTuilePlateau;
    }

    public void setTuileAt(int i, int j, Tuile tuile){
        this.plateau[i][j] = tuile;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight(){
        return height;
    }
    public Tuile[][] getPlateau() {
        return this.plateau;
    }
    public TuileShape getTypeTuilePlateau() {return typeTuilePlateau;}
}
