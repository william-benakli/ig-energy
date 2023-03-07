package model;

import model.typeenum.*;

import java.awt.image.BufferedImage;

public class Tuile {
    /**
     *
     * Class Tuile:
     *
     */

    private final TuileComposant composant;
    private DirectionInterface direction;
    private boolean connexionBoolean[];
    private final TuileShape shape;
    private boolean isActivated;


    private ConstructorBufferedTuile finalImage;

    public static class Builder{
        private TuileComposant composant;
        private DirectionInterface direction;
        private TuileShape shape;

        public Builder composantTuile(TuileComposant composant){
            this.composant = composant;
            return this;
        }
        public Builder directionTuile(DirectionInterface direction){
            this.direction = direction;
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
        this.direction = builder.direction;
        if(composant ==  TuileComposant.ENERGY) this.isActivated = true;
        else this.isActivated = false;
        connexionBoolean = new boolean[builder.direction.getSize()];
    }

    public BufferedImage getImage() {
        return finalImage;
    }

    public TuileComposant getComposant() {
        return composant;
    }

    public void rotation(){
        this.direction = direction.rotation();
    }

    @Override
    public String toString() {
        return "Tuile{" + "typeTuile=" + composant + ", finalImage=" + finalImage + "direction=" +  direction + '}' ;
    }


    public static void main(String[] args) {
        System.out.println(" ----------------- ");
        Tuile tcarre = new Builder().composantTuile(TuileComposant.ENERGY)
                .shapeTuile(TuileShape.CARRE)
                .directionTuile(DirCarre.NORD).build();
        Tuile tHexa = new Builder().composantTuile(TuileComposant.ENERGY).shapeTuile(TuileShape.HEXA).directionTuile(DirHexa.NORD).build();
        System.out.println("tuile carre " + tcarre);
        System.out.println("tuile hexa " + tHexa);
        tcarre.rotation();
        tHexa.rotation();
        System.out.println("tuile carre " + tcarre);
        System.out.println("tuile hexa " + tHexa);

    }


}
