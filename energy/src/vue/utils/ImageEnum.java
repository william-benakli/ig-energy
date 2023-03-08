package vue.utils;


import controler.Controller;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public enum ImageEnum {

    SQUARE_OFF_EMPTY(0, 0),
    SQUARE_OFF_COMPOSANT_WIFI(1, 1),
    SQUARE_OFF_COMPOSANT_LAMP(2, 1),
    SQUARE_OFF_Line_COMPOSANT(0, 2),
    SQUARE_OFF_ARC(1, 2),
    SQUARE_OFF_LINE(2, 2),
    SQUARE_ON_SQUARE_EMPTY(0, 3),
    SQUARE_ON_COMPOSANT_ENERGY(0, 4),
    SQUARE_ON_COMPOSANT_WIFI(1, 4),
    SQUARE_ON_COMPOSANT_LAMP(2, 4),
    SQUARE_ON_Line_COMPOSANT(0, 5),
    SQUARE_ON_ARC(1, 5),
    SQUARE_ON_LINE(2, 5),
    HEXAGON_OFF_EMPTY(3, 0),
    HEXAGON_OFF_COMPOSANT_WIFI(4, 1),
    HEXAGON_OFF_COMPOSANT_LAMP(5, 1),
    HEXAGON_OFF_Line_COMPOSANT(3, 2),
    HEXAGON_OFF_ARC(4, 2),
    HEXAGON_OFF_LINE(5, 2),
    HEXAGON_ON_HEXAGON_EMPTY(3, 3),
    HEXAGON_ON_COMPOSANT_ENERGY(3, 4),
    HEXAGON_ON_COMPOSANT_WIFI(4, 4),
    HEXAGON_ON_COMPOSANT_LAMP(5, 4),
    HEXAGON_ON_Line_COMPOSANT(3, 5),
    HEXAGON_ON_ARC(4, 5),
    HEXAGON_ON_LINE(5, 5);

    private BufferedImage image;

    ImageEnum(int x, int y) {
        try {
            image = ImageIO.read(Controller.file).getSubimage(x * 120, y * 120, 120, 120);
        } catch (Exception e) {
            System.out.println("Le Fichier chargé est null");
        }
    }

    public BufferedImage    getImage() {
        return image;
    }

}