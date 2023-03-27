package model.typeenum;

import model.Level;
import vue.Geometrie;
import vue.MenuJPanel;

import java.awt.*;
import java.util.ArrayList;

public interface DirectionInterface {

    static void paintComponent(Level level, int width, int height, int size, Graphics g, ArrayList<Geometrie> list, MenuJPanel menuJPanel) {

    }

    private static Polygon drawPolygon(int x, int y, int size) {
        return null;
    }

    int getSize();

    int getPosition();

    DirectionInterface[] getValues();

    default DirectionInterface rotation() {
        return getValues()[(this.getPosition() + 1) % getSize()];
    }

    int getPosJFromPos(DirectionInterface directionInterface, int column);

}
