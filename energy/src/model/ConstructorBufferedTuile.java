package model;

import model.typeenum.ImageEnum;
import model.typeenum.TuileComposant;
import model.typeenum.TuileShape;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ConstructorBufferedTuile extends BufferedImage {


    public ConstructorBufferedTuile(TuileShape type, TuileComposant composant, int ... pos) {
        super(120, 120, BufferedImage.TYPE_INT_ARGB);
        Graphics g = super.getGraphics();
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, 120, 120);
        g.drawImage(type.getImage(), 0, 0, null);

        if (type == TuileShape.CARRE) {
           // System.out.println("on est un carre");
            if (composant == TuileComposant.WIFI) {
                g.drawImage(ImageEnum.SQUARE_ON_COMPOSANT_WIFI.getImage(), 60, 60, null);
            } else if (composant == TuileComposant.ENERGY) {
                g.drawImage(ImageEnum.SQUARE_ON_COMPOSANT_ENERGY.getImage(), 60, 60, null);
            } else if (composant == TuileComposant.LIGHT) {
                g.drawImage(ImageEnum.SQUARE_OFF_COMPOSANT_LAMP.getImage(), 60, 60, null);
            }
        } else if (type == TuileShape.HEXA) {
            if (composant == TuileComposant.WIFI) {
                g.drawImage(ImageEnum.HEXAGON_OFF_COMPOSANT_WIFI.getImage(), 60, 60, null);
            } else if (composant == TuileComposant.ENERGY) {
                g.drawImage(ImageEnum.HEXAGON_ON_COMPOSANT_ENERGY.getImage(), 60, 60, null);
            } else if (composant == TuileComposant.LIGHT) {
                g.drawImage(ImageEnum.HEXAGON_OFF_COMPOSANT_LAMP.getImage(), 60, 60, null);
            }
        }
        g.dispose();
    }
}
