package model;


import model.typeenum.DirectionInterface;
import model.typeenum.TuileComposant;
import model.typeenum.TuileShape;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public final class Level implements Serializable {

    /**
     * Classe Level represente un niveau de jeu
     *
     */

    private static int idLevelStatic = 0;
    private Tuile[][] plateau;
    private Joueur j;

    private int idLevel;
    private int width, height;
    private TuileShape typeTuilePlateau;

    public Level(int idLevel, int height, int width, TuileShape typeTuilePlateau){
        this.plateau = new Tuile[height][width];
        this.idLevel = idLevel;
        this.width = width;
        this.height = height;
        this.typeTuilePlateau = typeTuilePlateau;
        idLevelStatic++;
        initEmpty();
    }

    public Level(int height, int width, TuileShape typeTuilePlateau){
        this.plateau = new Tuile[height][width];
        this.idLevel = idLevelStatic++;
        this.width = width;
        this.height = height;
        this.typeTuilePlateau = typeTuilePlateau;
        initEmpty();
    }

    /**
     * Cette fonction inialise la plateau avec des tuiles vides
     */
    public void initEmpty(){
        for (int i = 0; i < height ; i++) {
            for (int j = 0; j < width; j++) {
                Tuile t =  new Tuile.Builder().shapeTuile(typeTuilePlateau).composantTuile(TuileComposant.EMPTY).build();
                plateau[i][j] = t;
            }
        }
    }

    /**
     * Fonction de randomisation des tuiles plateaux
     * Pour chaque tuile elle applique n fois une rotation (ou n est un nombre aléatoire)
     */
    public void randomised(){
        for (int i = 0; i < height ; i++) {
            for (int j = 0; j < width; j++) {
                int r = new Random().nextInt(5);
                for (int k = 0; k <  r; k++) {
                    plateau[i][j].rotation();
                    plateau[i][j].update();
                }
            }
        }

    }

    /**
     * Fonction de propagation qui pour chaque source appel la fonction turnTuileOn(tuile, x, y);
     *
     */
    public void propagation(){
       turnOffBoard();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (plateau[i][j].getComposant() == TuileComposant.ENERGY) {
                    turnTuileOn(plateau[i][j], i, j);
                }
            }
        }
    }

    /**
     * Cette fonction recursive allume la tuile courante et appel de maniere recursif les voisins
     * connecté à cette tuile.
     * Elle prend en compte les cas particuliers :
     * -Si un wifi est allumé alors on allume tous les composants wifi du plateau
     * @param t est une tuile dans la plateau
     * @param i position i dans le plateau
     * @param j position j dans le plateau
     */
    public void turnTuileOn(Tuile t, int i, int j){
        t.powerOn();
        if(t.getComposant() == TuileComposant.WIFI) wifiPropagation();
        for(DirectionInterface dir :  t.getDirection().getValues()){
            Position position = dir.getPositionIJ(i, j);
            int ni = position.i();
            int nj = position.j();
            if((ni >= 0 && ni < height) && (nj >= 0 && nj < width)){
                Tuile neighbor = plateau[ni][nj];
                if(t.isConnected(neighbor.getEdge(), dir)){
                    if(neighbor.isPowerOff())turnTuileOn(neighbor, ni, nj);
                }
            }
        }
        t.update();
    }

    /**
     * Cette fonction s'active uniquement si un composant de type WIFI s'active,
     * alors toutes les cases WIFI auront le comportement d'un composant ENERGY
     */
    private void wifiPropagation(){
        for (int i = 0; i <height; i++) {
            for (int k = 0; k < width; k++) {
                if(plateau[i][k].getComposant() == TuileComposant.WIFI){
                    if(plateau[i][k].isPowerOff()){
                        turnTuileOn(plateau[i][k], i, k);
                    }
                }
            }
        }
    }

    /**
     * Cette fonction met toutes les tuiles en off sauf les tuiles energy
     */
    private void turnOffBoard(){
        for (int i = 0; i < height; i++) {
            for (int k = 0; k < width; k++) {
                plateau[i][k].powerOff();
            }
        }
    }

    /**
     * Cette fonction verifie que toutes les tuiles sont allumés donc une fin de parti
     */
    public boolean endGame(){
        for (int i = 0; i < height; i++) {
            for (int k = 0; k < width; k++) {
                if(plateau[i][k].getComposant() != TuileComposant.EMPTY){
                    if(plateau[i][k].isPowerOff())return false;
                }
            }
        }
        return true;
    }

    /**
     * Cette fonction met à jour graphiquement toutes les tuiles
     */
    public void updateAll() {
        for (int i = 0; i < height; i++) {
            for (int k = 0; k < width; k++) {
                plateau[i][k].update();
            }
        }
    }

    /** Setteur & G etteur **/

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
