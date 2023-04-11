package model;

import model.typeenum.*;
import vue.utils.ConstructorBufferedTuile;

import java.awt.image.BufferedImage;

public class Tuile {
    /**
     * Class Tuile:
     */

    private final TuileComposant composant;
    private final TuileShape shape;
    private DirectionInterface direction;
    private ConstructorBufferedTuile finalImage;
    private final boolean[] edgeBoolean;
    private boolean power;

    private Tuile(Builder builder) {
        this.shape = builder.shape;
        this.composant = builder.composant;
        this.power = (composant == TuileComposant.ENERGY);
        if (shape == TuileShape.HEXA) direction = DirHexa.NORD;
        else direction = DirCarre.NORD;
        edgeBoolean = new boolean[direction.getSize()];
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

    public void update() {
        this.finalImage = new ConstructorBufferedTuile(shape, composant, edgeBoolean);
    }

    public BufferedImage getImage() {
        return finalImage;
    }

    public void powerOff(){
        if(this.composant != TuileComposant.ENERGY) this.power = false;
    }
    public void powerOn(){
        this.power = true;
    }


    public TuileComposant getComposant() {
        return composant;
    }

    //TODO changer la rotation avec decalage
    public void rotation() {
        System.out.println("rotation avant " + direction);
        this.direction = direction.rotation();
        System.out.println("rotation apres " + direction);
        //int decalage = (direction.getSize() - direction.getPosition() )% 6;
        boolean tmp = edgeBoolean[edgeBoolean.length - 1];
        for (int i = edgeBoolean.length - 1; i > 0; i--)
            edgeBoolean[i] = edgeBoolean[i - 1];
        edgeBoolean[0] = tmp;
        update();
    }

    @Override
    public String toString() {
        return "Tuile{" + "typeTuile=" + composant + ", finalImage=" + finalImage + "direction=" + direction + '}';
    }

    public boolean isPowerOff() {
        return !power;
    }

    public boolean isConnected(boolean[] edge) {

        return false;
    }

    public static class Builder {
        private TuileComposant composant;
        private TuileShape shape;

        public Builder composantTuile(TuileComposant composant) {
            this.composant = composant;
            return this;
        }

        public Builder shapeTuile(TuileShape shape) {
            this.shape = shape;
            return this;
        }

        public Tuile build() {
            return new Tuile(this);
        }
    }

}
