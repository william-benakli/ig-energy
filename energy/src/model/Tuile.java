package model;

import model.typeenum.DirectionInterface;
import model.typeenum.ImageEnum;
import model.typeenum.TuileComposant;

import java.awt.image.BufferedImage;

public abstract class Tuile {
    /**
     *
     * Class Tuile:
     *
     */

    protected TuileComposant typeTuile;
    protected ConstructorBufferedTuile finalImage;

    public BufferedImage getImage() {
        return finalImage;
    }

    public TuileComposant getComposant() {
        return typeTuile;
    }

    @Override
    public String toString() {
        return "Tuile{" +
                "typeTuile=" + typeTuile +
                ", finalImage=" + finalImage +
                '}';
    }

    /**
     * Fonction qui va rotate les elements du tableau
     *
     */


}
