package vue.utils;

import model.typeenum.TuileComposant;
import model.typeenum.TuileShape;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ConstructorBufferedTuile extends BufferedImage {


    public ConstructorBufferedTuile(TuileShape type, TuileComposant composant, boolean[] edge) {
        super(120, 120, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) super.getGraphics();
        g.setColor(new Color(0, 0, 0, 0));
        g.fillRect(0, 0, 120, 120);
        g.drawImage(type.getImage(), 0, 0, null);


        if (type == TuileShape.CARRE) {
            if (composant == TuileComposant.WIFI) {
                g.drawImage(ImageEnum.SQUARE_ON_COMPOSANT_WIFI.getImage(), 0, 0, null);
            } else if (composant == TuileComposant.ENERGY) {
                g.drawImage(ImageEnum.SQUARE_ON_COMPOSANT_ENERGY.getImage(), 0, 0, null);
            } else if (composant == TuileComposant.LIGHT) {
                g.drawImage(ImageEnum.SQUARE_OFF_COMPOSANT_LAMP.getImage(), 0, 0, null);
            }
            if (composant == TuileComposant.EMPTY) {
                for (int i = 0; i < edge.length; i++) {
                    if (edge[i]) {
                        Graphics2D graphics2d = (Graphics2D) g.create();
                        graphics2d.rotate(Math.toRadians(i * 90), 60, 60);
                        if (edge[(i + 1) % edge.length]) {
                            graphics2d.drawImage(ImageEnum.SQUARE_OFF_ARC.getImage(), 0, 0, null);
                        } else if (i < edge.length - 1 && edge[(i + 2) % edge.length]) {
                            graphics2d.drawImage(ImageEnum.SQUARE_OFF_LINE.getImage(), 0, 0, null);
                        }
                    }
                }
            } else {
                for (int i = 0; i < edge.length; i++) {
                    if (edge[i]) {
                        Graphics2D graphics2d = (Graphics2D) g.create();
                        graphics2d.rotate(Math.toRadians(i * 90), 60, 60);
                        graphics2d.drawImage((composant != TuileComposant.ENERGY) ? ImageEnum.SQUARE_OFF_Line_COMPOSANT.getImage() : ImageEnum.SQUARE_ON_Line_COMPOSANT.getImage(), 0, 0, null);
                    }
                }
            }
        } else if (type == TuileShape.HEXA) {
            if (composant == TuileComposant.WIFI) {
                g.drawImage(ImageEnum.HEXAGON_OFF_COMPOSANT_WIFI.getImage(), 0, 0, null);
            } else if (composant == TuileComposant.ENERGY) {
                g.drawImage(ImageEnum.HEXAGON_ON_COMPOSANT_ENERGY.getImage(), 0, 0, null);
            } else if (composant == TuileComposant.LIGHT) {
                g.drawImage(ImageEnum.HEXAGON_OFF_COMPOSANT_LAMP.getImage(), 0, 0, null);
            }
            if (composant == TuileComposant.EMPTY) {
                for (int i = 0; i < edge.length; i++) {
                    if (edge[i]) {
                        Graphics2D graphics2d = (Graphics2D) g.create();
                        graphics2d.rotate(Math.toRadians(i * 60), 60, 52);

                        if (edge[(i + 1) % edge.length]) {
                            graphics2d.drawImage(ImageEnum.HEXAGON_OFF_ARC.getImage(), 0, 0, null);
                        } else if (i < edge.length - 1 && edge[(i + 2) % edge.length]) {
                            graphics2d.drawImage(ImageEnum.HEXAGON_OFF_LINE.getImage(), 0, 0, null);
                        } else if (i < edge.length - 2 && edge[(i + 3) % edge.length]) {
                            graphics2d.drawImage(ImageEnum.HEXAGON_OFF_LINE_LONG.getImage(), 0, 0, null);
                        }

                    }
                }
            } else {
                for (int i = 0; i < edge.length; i++) {
                    if (edge[i]) {
                        Graphics2D graphics2d = (Graphics2D) g.create();
                        graphics2d.rotate(Math.toRadians(i * 60), 60, 51);
                        graphics2d.drawImage((composant != TuileComposant.ENERGY) ? ImageEnum.HEXAGON_OFF_Line_COMPOSANT.getImage() : ImageEnum.HEXAGON_ON_Line_COMPOSANT.getImage(), 0, 0, null);
                    }
                }
            }
        }
        g.dispose();

    }
}
