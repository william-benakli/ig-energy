package controler;

import model.*;
import model.typeenum.DirectionInterface;
import vue.BoardViewGame;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ControllerEditBoard extends Controller{

    private int posX,posY;

    public ControllerEditBoard(Level level, BufferedModel model, BoardViewGame gameView) {
        super(level, model, gameView);
    }

    @Override
    public void mousePressed(MouseEvent e) {
            for (Geometrie geo : list) {
                if (geo.getPolygon().contains(e.getPoint()))
                    tuileCourante = level.getPlateau()[geo.getDeducY()][geo.getDeducX()];
            }
            this.posX = e.getX();
            this.posY = e.getY();
            Graphics2D graphics2D = (Graphics2D) model.getGraphics();
            graphics2D.setColor(Color.white);
            graphics2D.setStroke(new BasicStroke(15));
            graphics2D.drawLine(posX, posY, posX, posY);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
            for (Geometrie geo : list) {
                if (geo.getPolygon().contains(e.getPoint())) {
                    Tuile mousePosition = level.getPlateau()[geo.getDeducY()][geo.getDeducX()];
                    if (tuileCourante != mousePosition) {
                        for (DirectionInterface dir : tuileCourante.getDirection().getValues()) {
                            Position position = dir.getPositionIJ(tuileCourante.getI(), tuileCourante.getJ());
                            int ni = position.i();
                            int nj = position.j();
                            if (ni >= 0 && ni < level.getHeight() && nj >= 0 && nj < level.getWidth()) {
                                Tuile neighbor = level.getPlateau()[ni][nj];
                                if (neighbor == mousePosition) {
                                    tuileCourante.setEdgeBoolean(dir.getPosition(), true);
                                    neighbor.setEdgeBoolean(dir.getOpositeDirection(dir.getPosition()), true);
                                    tuileCourante = level.getPlateau()[geo.getDeducY()][geo.getDeducX()];
                                }
                            }
                        }
                    }
                }
                Graphics2D graphics2D = (Graphics2D) model.getGraphics();
                graphics2D.setColor(Color.white);
                graphics2D.setStroke(new BasicStroke(15));
                graphics2D.drawLine(posX, posY, e.getX(), e.getY());
                this.posX = e.getX();
                this.posY = e.getY();
                level.updateAll();
                gameView.repaint();
            }
        }

}
