package model;

import model.typeenum.DirectionInterface;
import model.typeenum.ImageEnum;
import model.typeenum.TuileComposant;
import model.typeenum.TuileShape;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

public class Tuile {
    /**
     *
     * Class Tuile:
     *
     */

    private final TuileComposant composant;
    private final ArrayList<DirectionInterface> list_direction;
    private final TuileShape shape;
    private boolean isActivated;


    private ConstructorBufferedTuile finalImage;

    public class Builder{
        private TuileComposant composant;
        private ArrayList<DirectionInterface> list_direction;
        private TuileShape shape;

        public Builder composantTuile(TuileComposant composant){
            this.composant = composant;
            return this;
        }
        public Builder directionTuile(ArrayList<DirectionInterface> list_direction){
            this.list_direction = list_direction;
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
        this.list_direction = builder.list_direction;
        if(composant ==  TuileComposant.ENERGY) this.isActivated = true;
        else this.isActivated = false;
    }

    public BufferedImage getImage() {
        return finalImage;
    }

    public void addDirectionPos(DirectionInterface directionInterface){
        list_direction.add(directionInterface);
    }

    public TuileComposant getComposant() {
        return composant;
    }

    @Override
    public String toString() {
        return "Tuile{" + "typeTuile=" + composant + ", finalImage=" + finalImage + '}';
    }

    public void rotate(){

    }


}
