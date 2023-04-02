package model.typeenum;

import model.Level;
import model.Geometrie;
import vue.GameJPanel;

import java.awt.*;
import java.util.ArrayList;

public interface DirectionInterface {

    static void paintComponent(Level level, int width, int height, int size, Graphics g, ArrayList<Geometrie> list, GameJPanel menuJPanel) {

    }

    int getSize();
    int getPosition();
    DirectionInterface[] getValues();
    default DirectionInterface rotation() {
        return getValues()[(this.getPosition() + 1) % getSize()];
    }
    int getPosJFromPos(DirectionInterface directionInterface, int column);

}
