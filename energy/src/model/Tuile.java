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
    private boolean isActivated;

    private Tuile(Builder builder) {
        this.shape = builder.shape;
        this.composant = builder.composant;
        this.isActivated = (composant == TuileComposant.ENERGY);
        if (shape == TuileShape.HEXA) direction = DirHexa.NORD;
        else direction = DirCarre.NORD;
        edgeBoolean = new boolean[direction.getSize()];
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

    public TuileComposant getComposant() {
        return composant;
    }

    //TODO changer la rotation avec decalage
    public void rotation() {
        this.direction = direction.rotation();
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
