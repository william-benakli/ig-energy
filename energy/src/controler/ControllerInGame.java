package controler;

import model.BufferedModel;
import model.Geometrie;
import model.Level;
import vue.BoardViewGame;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ControllerInGame extends Controller{

    public ControllerInGame(Level level, BufferedModel model, BoardViewGame gameView) {
        super(level, model, gameView);
        getPlayer().start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (Geometrie geo : list) {
            if (geo.getPolygon().contains(e.getPoint())) {
                level.getPlateau()[geo.getDeducY()][geo.getDeducX()].rotation();
                level.propagation();
                level.updateAll();
                if(level.endGame()){
                    getPlayer().stop(level.getNameLevel());
                }
                gameView.repaint();
            }
        }
        super.mouseClicked(e);
        model.notifyObserver();
    }
}
