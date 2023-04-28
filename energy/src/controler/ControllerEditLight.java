package controler;

import model.BufferedModel;
import model.Geometrie;
import model.Level;
import model.typeenum.TuileComposant;
import vue.BoardViewGame;

import java.awt.event.MouseEvent;

public class ControllerEditLight extends Controller{

    public ControllerEditLight(Level level, BufferedModel model, BoardViewGame gameView) {
        super(level, model, gameView);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (Geometrie geo : list) {
            if (geo.getPolygon().contains(e.getPoint()))
                level.getPlateau()[geo.getDeducY()][geo.getDeducX()].setComposant(TuileComposant.LIGHT);
        }
        level.propagation();
        level.updateAll();
        gameView.repaint();
    }
}