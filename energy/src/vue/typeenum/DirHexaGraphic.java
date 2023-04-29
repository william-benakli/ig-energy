package vue.typeenum;

import controler.Controller;
import model.Geometrie;
import model.Level;
import model.Tuile;
import model.typeenum.TuileComposant;
import vue.utils.ImageEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DirHexaGraphic implements DirectionInterfaceGraphic {

    public static void paintComponent(Level level, int width, int height, int size, Graphics2D g, Controller controller, JPanel menuJPanel) {
        for (Geometrie geo : controller.getList()) {
            int col = geo.getDeducY();
            int row = geo.getDeducX();
            int x = row * size + (width - ((level.getWidth()) * size)) / 2 + (size / 2 * ((int) level.getWidth() / 2)) / 2 - (size / 4) * row;
            int y = col * size + (height - ((level.getHeight()) * size)) / 2 + size / 3 - (size / 7) * col;
            if (row % 2 == 0 || col < level.getHeight() - 1) {
                if (row % 2 == 1) y += (size / 2 - size / 12);
                g.drawImage(level.getPlateau()[col][row].getImage(), x, y, size, size, menuJPanel);
                g.setColor(Color.red);
                g.drawPolygon(geo.getPolygon());
            }
        }

    }

    public static Polygon createPolygon(int x, int y, int size) {
        Polygon hexagon = new Polygon();
        x += size / 2;
        y += size / 2;
        for (int i = 0; i < 6; i++) {
            hexagon.addPoint(
                    (int) (x + size / 2 * Math.cos(i * 2 * Math.PI / 6D)),
                    (int) (y + size / 2 * Math.sin(i * 2 * Math.PI / 6D))
            );
        }
        return hexagon;
    }

    public static void tuileImage(Tuile t, Graphics2D g) {
        final TuileComposant composant = t.getComposant();
        boolean[] edge = t.getEdge();
        boolean isconnected = t.getPower();

        if (composant == TuileComposant.EMPTY) {

            if(isconnected){
                for (int i = 0; i < edge.length; i++) {
                    if (edge[i]) {
                        Graphics2D graphics2d = (Graphics2D) g.create();
                        graphics2d.rotate(Math.toRadians(i * 60), 60, 52);

                        if (edge[(i + 1) % edge.length]) {
                            graphics2d.drawImage(ImageEnum.HEXAGON_ON_ARC.getImage(), 0, 0, null);
                        } else if (edge[(i + 2) % edge.length]) {
                            graphics2d.drawImage(ImageEnum.HEXAGON_ON_LINE.getImage(), 0, 0, null);
                        } else if (edge[(i + 3) % edge.length]) {
                            graphics2d.drawImage(ImageEnum.HEXAGON_ON_LINE_LONG.getImage(), 0, 0, null);
                        }

                    }
                }
            }else{
                for (int i = 0; i < edge.length; i++) {
                    if (edge[i]) {
                        Graphics2D graphics2d = (Graphics2D) g.create();
                        graphics2d.rotate(Math.toRadians(i * 60), 60, 52);

                        if (edge[(i + 1) % edge.length]) {
                            graphics2d.drawImage(ImageEnum.HEXAGON_OFF_ARC.getImage(), 0, 0, null);
                        } else if (edge[(i + 2) % edge.length]) {
                            graphics2d.drawImage(ImageEnum.HEXAGON_OFF_LINE.getImage(), 0, 0, null);
                        } else if (edge[(i + 3) % edge.length]) {
                            graphics2d.drawImage(ImageEnum.HEXAGON_OFF_LINE_LONG.getImage(), 0, 0, null);
                        }

                    }
                }
            }

        } else {
            if(isconnected){
                BufferedImage bf;
                if (composant == TuileComposant.WIFI) bf = ImageEnum.HEXAGON_ON_COMPOSANT_WIFI.getImage();
                else if (composant == TuileComposant.ENERGY) bf = ImageEnum.HEXAGON_ON_COMPOSANT_ENERGY.getImage();
                else bf = ImageEnum.HEXAGON_ON_COMPOSANT_LAMP.getImage();
                g.drawImage(bf, 0, 0, null);

                for (int i = 0; i < edge.length; i++) {
                    if (edge[i]) {
                        Graphics2D graphics2d = (Graphics2D) g.create();
                        graphics2d.rotate(Math.toRadians(i * 60), 60, 51);
                        graphics2d.drawImage(ImageEnum.HEXAGON_ON_Line_COMPOSANT.getImage(), 0, 0, null);
                    }
                }
            }else{
                BufferedImage bf;
                if (composant == TuileComposant.WIFI) bf = ImageEnum.HEXAGON_OFF_COMPOSANT_WIFI.getImage();
                else if (composant == TuileComposant.ENERGY) bf = ImageEnum.HEXAGON_ON_COMPOSANT_ENERGY.getImage();
                else bf = ImageEnum.HEXAGON_OFF_COMPOSANT_LAMP.getImage();
                g.drawImage(bf, 0, 0, null);

                for (int i = 0; i < edge.length; i++) {
                    if (edge[i]) {
                        Graphics2D graphics2d = (Graphics2D) g.create();
                        graphics2d.rotate(Math.toRadians(i * 60), 60, 51);
                        graphics2d.drawImage((composant != TuileComposant.ENERGY) ? ImageEnum.HEXAGON_OFF_Line_COMPOSANT.getImage() : ImageEnum.HEXAGON_ON_Line_COMPOSANT.getImage(), 0, 0, null);
                    }
                }
            }
        }
    }
}
