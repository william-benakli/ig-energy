package model;

public final class Joueur {

    private String name;
    private int points;

    public Joueur(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getPoints(){
        return points;
    }

    public void setPoints(int points){
        this.points = points;
    }

    public void addPoints(int points){
        this.points+=points;
    }
}
