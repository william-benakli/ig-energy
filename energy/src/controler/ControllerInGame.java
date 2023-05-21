package controler;

import model.BufferedModel;
import model.Geometrie;
import model.Level;
import vue.BoardViewGame;

import java.awt.event.MouseEvent;
import java.security.spec.RSAOtherPrimeInfo;

public class ControllerInGame extends Controller{

    private ControllerMenu menuController;

    public ControllerInGame(Level level, BufferedModel model, BoardViewGame gameView, ControllerMenu menuController) {
        super(level, model, gameView);
        getPlayer().start();
        this.menuController = menuController;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(level.endGame())return;

            for (Geometrie geo : list) {
            if (geo.getPolygon().contains(e.getPoint())) {
                level.getPlateau()[geo.getDeducY()][geo.getDeducX()].rotation();
                level.propagation();
                level.updateAll();
                if(level.endGame()){
                    System.out.println("Fin de partie");
                    gameView.getLabel().transitionText(5, "Niveau terminé bravo !");
                    if(menuController.listLvl.size() > getPlayer().getLevelMax() && menuController.listLvl.get(getPlayer().getLevelMax()-1).getNameLevel().equals(level.getNameLevel())){
                        getPlayer().levelUp();
                    }
                    getPlayer().stop(level.getNameLevel());
                }
                gameView.repaint();
            }
        }
        super.mouseClicked(e);
        model.notifyObserver();
    }
}
