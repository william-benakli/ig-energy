package vue;

import model.Geometrie;
import model.Level;
import model.Tuile;
import model.typeenum.TuileShape;
import vue.typeenum.DirCarreGraphic;
import vue.typeenum.DirHexaGraphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

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
        addMouseListener(this);
        addMouseMotionListener(this);
        setPreferredSize(new Dimension(level.getWidth() * 120, level.getHeight() * 120));
    }

    @Override
    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, getSize().width, getSize().height);
        list.clear();

        if(tuileCourante != null) System.out.println(tuileCourante.getComposant());

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
                level.getPlateau()[geo.getDeducY()][geo.getDeducX()].rotation();
                level.propagation();
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
        for (Geometrie geo : list) {
            if (geo.getPolygon().contains(e.getPoint())){
                tuileCourante = level.getPlateau()[geo.getDeducY()][geo.getDeducX()];
                System.out.println("changement mp");
            }
        }
        this.posX = e.getX();
        this.posY = e.getY();
        ((Graphics2D) getGraphics()).setStroke(new BasicStroke(30));
        getGraphics().drawLine(posX, posY, posX, posY);
        //notifyObserver();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        for (Geometrie geo : list) {
            if (geo.getPolygon().contains(e.getPoint()))
                if(tuileCourante != level.getPlateau()[geo.getDeducY()][geo.getDeducX()]){
                    //recuperer le voisin
                    //mettre son boolean à la bonne position à true
                    //appeler ton algo de connector
                    //changer la tuile courante
                    System.out.println("changement md");
                    tuileCourante = level.getPlateau()[geo.getDeducY()][geo.getDeducX()];
                }
        }
        getGraphics().drawLine(posX, posY, e.getX(), e.getY());
        this.posX = e.getX();
        this.posY = e.getY();
        //model.notifyObserver();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

}