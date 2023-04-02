package vue.typeenum;

import model.Geometrie;
import model.Level;
import model.typeenum.TuileComposant;
import vue.GameJPanel;

import java.awt.*;
import java.util.ArrayList;

public interface DirectionInterfaceGraphic {

    static void paintComponent(Level level, int width, int height, int size, Graphics g, ArrayList<Geometrie> list, GameJPanel menuJPanel) {

    }

    public static void tuileImage(TuileComposant composant, boolean[] edge, Graphics2D g) {

    }

}
