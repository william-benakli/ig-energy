package model;

import model.typeenum.*;
import vue.utils.ConstructorBufferedTuile;

import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Tuile {
    /**
     * Class Tuile:
     */

    private TuileComposant composant;
    private final TuileShape shape;
    private DirectionInterface direction;
    private ConstructorBufferedTuile finalImage;
    private boolean[] edgeBoolean;
    private boolean power;
    private Position position;

    public Tuile(TuileShape shapeTuile, TuileComposant composant, DirectionInterface direction, Position position) {
        this.shape = shapeTuile;
        this.composant = composant;
        this.power = (composant == TuileComposant.ENERGY);
        this.direction = direction;
        this.position = position;
        edgeBoolean = new boolean[direction.getSize()];
    }

    public void update() {
        this.finalImage = new ConstructorBufferedTuile(shape, composant, this, edgeBoolean);
    }

    public void powerOff(){
        if(this.composant != TuileComposant.ENERGY) this.power = false;
    }
    public void powerOn(){
        this.power = true;
    }

    public void rotation() {
        if(composant == TuileComposant.ENERGY)return;
        boolean tmp = edgeBoolean[edgeBoolean.length - 1];
        for (int i = edgeBoolean.length - 1; i > 0; i--)
            edgeBoolean[i] = edgeBoolean[i - 1];
        edgeBoolean[0] = tmp;
        update();
    }

    public boolean isConnected(boolean[] edge, DirectionInterface dir) {
        return edgeBoolean[dir.getPosition()] && edge[dir.getOpositeDirection(dir.getPosition())];
    }

    /*Getteur and setteur*/

    public BufferedImage getImage() {
        return finalImage;
    }

    public TuileComposant getComposant() {
        return composant;
    }

    public int getJ(){
        return position.j();
    }
    public int getI(){
        return position.i();
    }
    public DirectionInterface getDirection(){
        return direction;
    }
    public boolean[] getEdge() {
        return edgeBoolean;
    }

    public void setEdgeBoolean(int pos, boolean value) {
        edgeBoolean[pos] = value;
    }
    @Override
    public String toString() {
        return "Tuile{" + "typeTuile=" + composant + ", finalImage=" + finalImage + "direction=" + direction + '}';
    }

    public boolean isPowerOff() {
        return !power;
    }

    public boolean getPower() {
        return power;
    }

    public void setComposant(TuileComposant composant) {
        this.composant = composant;
    }

    public void restore() {
        composant = TuileComposant.EMPTY;
        edgeBoolean = new boolean[direction.getSize()];
        powerOff();
    }
}
