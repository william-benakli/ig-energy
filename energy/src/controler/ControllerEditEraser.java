package controler;

import model.BufferedModel;
import model.Geometrie;
import model.Level;
import model.typeenum.TuileComposant;
import vue.BoardViewGame;

import java.awt.event.MouseEvent;

public class ControllerEditEraser extends Controller{

    public ControllerEditEraser(Level level, BufferedModel model, BoardViewGame gameView) {
        super(level, model, gameView);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (Geometrie geo : list) {
            if (geo.getPolygon().contains(e.getPoint()))
                level.getPlateau()[geo.getDeducY()][geo.getDeducX()].restore();
        }
        level.propagation();
        level.updateAll();
        gameView.repaint();
    }
}