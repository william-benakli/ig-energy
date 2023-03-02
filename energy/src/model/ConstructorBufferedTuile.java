package model;

import model.typeenum.ImageEnum;
import model.typeenum.TuileComposant;
import model.typeenum.TuileShape;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ConstructorBufferedTuile extends BufferedImage {


    public ConstructorBufferedTuile(TuileShape type, TuileComposant composant, ArrayList<Integer> pos) {
        super(120, 120, BufferedImage.TYPE_INT_ARGB);
        Graphics g = super.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 120, 120);

        if (type == TuileShape.CARRE) {
            if (composant == TuileComposant.WIFI) {
                g.drawImage(ImageEnum.SQUARE_ON_COMPOSANT_WIFI.getImage(), 0, 0, null);
            } else if (composant == TuileComposant.ENERGY) {
                g.drawImage(ImageEnum.SQUARE_ON_COMPOSANT_ENERGY.getImage(), 0, 0, null);
            } else if (composant == TuileComposant.LIGHT) {
                g.drawImage(ImageEnum.SQUARE_OFF_COMPOSANT_LAMP.getImage(), 0, 0, null);
            }
        } else if (type == TuileShape.HEXA) {
            if (composant == TuileComposant.WIFI) {
                g.drawImage(ImageEnum.HEXAGON_OFF_COMPOSANT_WIFI.getImage(), 0, 0, null);
            } else if (composant == TuileComposant.ENERGY) {
                g.drawImage(ImageEnum.HEXAGON_ON_COMPOSANT_ENERGY.getImage(), 0, 0, null);
            } else if (composant == TuileComposant.LIGHT) {
                g.drawImage(ImageEnum.HEXAGON_OFF_COMPOSANT_LAMP.getImage(), 0, 0, null);
            }
        }

        g.drawImage(type.getImage(), 0, 0, null);
        System.out.println( " AVANT FORT " + pos.size());
        for (int position: pos){
            Graphics2D graphics2d = (Graphics2D) g;
            graphics2d.rotate(Math.toRadians(position*90), 60, 60);
            graphics2d.drawImage(ImageEnum.SQUARE_ON_Line_COMPOSANT.getImage(), 0, 0, null);
        }

        g.dispose();
        System.out.println( " APRES FORT " + pos.size());

    }

    public void rotate() {
        Graphics2D graphics2D = (Graphics2D) getGraphics();
        graphics2D.rotate(Math.toRadians(90), 60, 60);
    }
}
