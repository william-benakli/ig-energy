package vue;

import model.Geometrie;
import model.Level;
import model.Tuile;
import model.typeenum.DirectionInterface;
import model.typeenum.TuileShape;
import vue.typeenum.DirCarreGraphic;
import vue.typeenum.DirHexaGraphic;
import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class BoardViewGame extends JPanel implements MouseListener, MouseMotionListener {

    private final ArrayList<Geometrie> list = new ArrayList<>();
    private Level level;
    private boolean isGame;
    private int posX,posY;
    private Tuile tuileCourante;


    public BoardViewGame(Level level, boolean isGame) {
        this.isGame = isGame;
        this.level = level;
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 50));
        this.setBackground(GraphiqueBuilder.blackBackGround());
        addMouseListener(this);
        addMouseMotionListener(this);
        setPreferredSize(new Dimension(level.getWidth() * 120, level.getHeight() * 120));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        list.clear();
        if(tuileCourante != null){
            System.out.println(tuileCourante.getComposant());
        }

        int height = getSize().width / (level.getWidth());
        int width = getSize().height / (level.getHeight());
        int size = Math.min(width, height);

        if (level.getTypeTuilePlateau() == TuileShape.CARRE)
            DirCarreGraphic.paintComponent(level, getSize().width, getSize().height, size, g, list, this);
        else
            DirHexaGraphic.paintComponent(level, getSize().width, getSize().height, size, g, list, this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (Geometrie geo : list) {
            if (geo.getPolygon().contains(e.getPoint())) {
               // System.out.println(Arrays.toString(level.getPlateau()[geo.getDeducY()][geo.getDeducX()].getEdge()) + " fz");
                System.out.println(level.getPlateau()[geo.getDeducY()][geo.getDeducX()].getPower() + " composat "+ level.getPlateau()[geo.getDeducY()][geo.getDeducX()].getComposant());
                level.getPlateau()[geo.getDeducY()][geo.getDeducX()].rotation();
                level.propagation();

                level.updateAll();

                if(level.endGame()){
                    System.out.println("fin de partie");
                }
                repaint();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    @Override
    public void mousePressed(MouseEvent e) {
       /* for (Geometrie geo : list) {
            if (geo.getPolygon().contains(e.getPoint())){
                tuileCourante = level.getPlateau()[geo.getDeducY()][geo.getDeducX()];
            }
        }
        this.posX = e.getX();
        this.posY = e.getY();
        Graphics2D graphics2D = (Graphics2D) getGraphics();
        graphics2D.setColor(Color.white);
        graphics2D.setStroke(new BasicStroke(15));
        graphics2D.drawLine(posX, posY, posX, posY);*/
    }

    @Override
    public void mouseDragged(MouseEvent e) {
     /*   for (Geometrie geo : list) {
            if (geo.getPolygon().contains(e.getPoint()))
                if(tuileCourante != level.getPlateau()[geo.getDeducY()][geo.getDeducX()]){

                    for(DirectionInterface dir: tuileCourante.getDirection().getValues()){
                        int ni = dir.getI(geo.getDeducY(), geo.getDeducX());
                        int nj = dir.getJ(geo.getDeducY(), geo.getDeducX());
                        if(ni >= 0 && ni < level.getHeight() && nj >= 0 && nj < level.getWidth()){
                            Tuile neighbor = level.getPlateau()[ni][nj];
                            if(neighbor == level.getPlateau()[geo.getDeducY()][geo.getDeducX()]){
                                tuileCourante.setEdgeBoolean(dir.getPosition(), true);
                               // neighbor.setEdgeBoolean(dir.getOpositeDirection(), true);
                                tuileCourante.update();
                                neighbor.update();
                                revalidate();
                                repaint();
                            }
                        }
                    }
                    tuileCourante = level.getPlateau()[geo.getDeducY()][geo.getDeducX()];
                }
            Graphics2D graphics2D = (Graphics2D) getGraphics();
            graphics2D.setColor(Color.white);
            graphics2D.setStroke(new BasicStroke(15));
            graphics2D.drawLine(posX, posY, e.getX(), e.getY());
            this.posX = e.getX();
            this.posY = e.getY();
        }
        */
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

}