package model;

import model.typeenum.*;
import vue.utils.ConstructorBufferedTuile;

import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Collections;

public class Tuile {
    /**
     *
     * Class Tuile:
     *
     */

    private final TuileComposant composant;
    private DirectionInterface direction;
    private ConstructorBufferedTuile finalImage;

    private boolean edgeBoolean[];
    private final TuileShape shape;
    private boolean isActivated;

    public boolean[] getEdge() {
        return edgeBoolean;
    }

    public static class Builder{
        private TuileComposant composant;
        private TuileShape shape;

        public Builder composantTuile(TuileComposant composant){
            this.composant = composant;
            return this;
        }
        public Builder shapeTuile(TuileShape shape){
            this.shape = shape;
            return this;
        }

        public Tuile build(){
           return new Tuile(this);
        }
    }

    private Tuile(Builder builder){
        this.shape = builder.shape;
        this.composant = builder.composant;
        this.isActivated = (composant == TuileComposant.ENERGY);
        if(shape == TuileShape.HEXA)direction = DirHexa.NORD;
        else direction = DirCarre.NORD;
        edgeBoolean = new boolean[direction.getSize()];
    }

    public void setEdgeBoolean(int pos, boolean value){
        edgeBoolean[pos] = value;
    }

    public void update() {
        this.finalImage = new ConstructorBufferedTuile(shape, composant, edgeBoolean);
    }

    public BufferedImage getImage() {
        return finalImage;
    }

    public TuileComposant getComposant() {
        return composant;
    }
//TODO changer la rotation avec decalage
    public void rotation(){
        this.direction = direction.rotation();

    }

    @Override
    public String toString() {
        return "Tuile{" + "typeTuile=" + composant + ", finalImage=" + finalImage + "direction=" +  direction + '}' ;
    }

}
