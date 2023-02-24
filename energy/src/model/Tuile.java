package model;

import model.typeenum.DirectionInterface;
import model.typeenum.ImageEnum;
import model.typeenum.TuileComposant;

import java.awt.image.BufferedImage;

public final class Tuile {
    /**
     *
     * Class Tuile:
     *
     */

    private TuileComposant typeTuile;
    private DirectionInterface direction;
    private BufferedImage directionImage, typeComposantImage;
    private BufferedImage finalImage;

    public  Tuile(TuileComposant typeTuile, int ... pos){

    }

    public BufferedImage getImage() {
        return finalImage;
    }

    /**
     * Fonction qui va rotate les elements du tableau
     *
     */


}
