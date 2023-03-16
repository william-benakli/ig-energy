package model.typeenum;

import vue.utils.ImageEnum;

import java.awt.*;

public enum TuileShape {

    CARRE(ImageEnum.SQUARE_OFF_EMPTY.getImage()),
    HEXA(ImageEnum.HEXAGON_OFF_EMPTY.getImage());

    TuileShape(Image image){
        this.image = image;
    }

    private Image image;
    public Image getImage() {return image;}
}
