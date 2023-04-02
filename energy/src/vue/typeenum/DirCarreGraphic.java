package vue.typeenum;

import model.Geometrie;
import model.Level;
import model.typeenum.TuileComposant;
import vue.GameJPanel;
import vue.utils.ImageEnum;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DirCarreGraphic implements DirectionInterfaceGraphic {

    public static void paintComponent(Level level, int width, int height, int size, Graphics g, ArrayList<Geometrie> list, GameJPanel menuJPanel) {
        for (int row = 0; row < level.getWidth(); row++) {
            for (int col = 0; col < level.getHeight(); col++) {
                int x = row * size + (width - ((level.getWidth()) * size)) / 2;
                int y = col * size + (height - ((level.getHeight()) * size)) / 2;

                Geometrie geometriePolygon = new Geometrie(drawPolygon(x, y, size), row, col);
                list.add(geometriePolygon);
                g.setColor(Color.red);
                g.drawPolygon(geometriePolygon.getPolygon());

                g.drawImage(
                        level.getPlateau()[col][row].getImage(),
                        x,
                        y,
                        size,
                        size,
                        menuJPanel
                );
            }
        }
    }

    private static Polygon drawPolygon(int x, int y, int size) {
        Polygon hexagon = new Polygon();

        hexagon.addPoint(x, y);
        hexagon.addPoint(x + size, y);
        hexagon.addPoint(x + size, y + size);
        hexagon.addPoint(x, y + size);

        return hexagon;
    }

    public static void tuileImage(TuileComposant composant, boolean[] edge, Graphics2D g) {
        if (composant == TuileComposant.EMPTY) {
            for (int i = 0; i < edge.length; i++) {
                if (edge[i]) {
                    Graphics2D graphics2d = (Graphics2D) g.create();
                    graphics2d.rotate(Math.toRadians(i * 90), 60, 60);
                    if (edge[(i + 1) % edge.length]) {
                        graphics2d.drawImage(ImageEnum.SQUARE_OFF_ARC.getImage(), 0, 0, null);
                    } else if (edge[(i + 2) % edge.length]) {
                        graphics2d.drawImage(ImageEnum.SQUARE_OFF_LINE.getImage(), 0, 0, null);
                    }
                }
            }
        } else {
            BufferedImage bf;
            if (composant == TuileComposant.WIFI) bf = ImageEnum.SQUARE_ON_COMPOSANT_WIFI.getImage();
            else if (composant == TuileComposant.ENERGY) bf = ImageEnum.SQUARE_ON_COMPOSANT_ENERGY.getImage();
            else bf = ImageEnum.SQUARE_OFF_COMPOSANT_LAMP.getImage();
            g.drawImage(bf, 0, 0, null);

            for (int i = 0; i < edge.length; i++) {
                if (edge[i]) {
                    Graphics2D graphics2d = (Graphics2D) g.create();
                    graphics2d.rotate(Math.toRadians(i * 90), 60, 60);
                    graphics2d.drawImage((composant != TuileComposant.ENERGY) ? ImageEnum.SQUARE_OFF_Line_COMPOSANT.getImage() : ImageEnum.SQUARE_ON_Line_COMPOSANT.getImage(), 0, 0, null);
                }
            }
        }
    }

}
