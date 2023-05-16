package controler;

import model.BufferedModel;
import model.Geometrie;
import model.Level;
import model.typeenum.TuileComposant;
import vue.BoardViewGame;

import java.awt.event.MouseEvent;

public class ControllerEditWifi extends Controller{

    public ControllerEditWifi(Level level, BufferedModel model, BoardViewGame gameView) {
        super(level, model, gameView);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (Geometrie geo : list) {
            if (geo.getPolygon().contains(e.getPoint()))
                level.getPlateau()[geo.getDeducY()][geo.getDeducX()].setComposant(TuileComposant.WIFI);
        }
        level.propagation();
        level.updateAll();
        super.mouseClicked(e);
        model.notifyObserver();
    }
}
