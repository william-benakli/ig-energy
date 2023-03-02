package model;

import model.typeenum.DirectionInterface;
import model.typeenum.ImageEnum;
import model.typeenum.TuileComposant;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

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

    public abstract void rotate();
    public abstract ArrayList<Integer> getListConnexion() ;


        /**
         * Fonction qui va rotate les elements du tableau
         *
         */


}
