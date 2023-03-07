package model;


import java.util.ArrayList;

public final class Level {

    private Tuile[][] plateau;
    private int idLevel;
    private Joueur j;
    private int weight, height;

    public Level(int idLevel, int weight, int height){
        this.plateau = new Tuile[weight][height];
        this.idLevel = idLevel;
        this.weight = weight;
        this.height = height;
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
}
