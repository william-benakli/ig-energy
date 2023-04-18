package vue;

import model.Geometrie;
import model.Level;
import model.Position;
import model.Tuile;
import model.typeenum.DirectionInterface;
import model.typeenum.EditeurSelector;
import model.typeenum.TuileComposant;
import model.typeenum.TuileShape;
import vue.editor.EditorSelectionItemJPanel;
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
    private EditorSelectionItemJPanel editorSelectionItemJPanel;


    public BoardViewGame(Level level, boolean isGame) {
        this.isGame = isGame;
        this.level = level;
        this.tuileCourante = level.getPlateau()[0][0];

        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 50));
        this.setBackground(GraphiqueBuilder.blackBackGround());
        addMouseListener(this);
        addMouseMotionListener(this);
        setPreferredSize(new Dimension(level.getWidth() * 120, level.getHeight() * 120));
    }

    public BoardViewGame(Level level, EditorSelectionItemJPanel editorSelectionItemJPanel, boolean isGame) {
        this(level, isGame);
        this.editorSelectionItemJPanel = editorSelectionItemJPanel;
    }

    public void setComposant(int ni, int nj , TuileComposant composant){
        if (ni >= 0 && ni < level.getHeight() && nj >= 0 && nj < level.getWidth()) {
            level.getPlateau()[ni][nj].setComposant(composant);
        }
        }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        list.clear();
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
        if(isGame && level.endGame())return;
        if(isGame){
            for (Geometrie geo : list) {
                if (geo.getPolygon().contains(e.getPoint())) {
                    level.getPlateau()[geo.getDeducY()][geo.getDeducX()].rotation();
                    level.propagation();
                    level.updateAll();
                    if(isGame && level.endGame()){
                        System.out.println("fin de partie");
                    }
                    repaint();
                }
            }
        }else {
            for (Geometrie geo : list) {
                if (geo.getPolygon().contains(e.getPoint())) {
                    switch (editorSelectionItemJPanel.getItemSelected()){
                        case ERASER -> level.getPlateau()[geo.getDeducY()][geo.getDeducX()].restore();
                        case WIFI -> level.getPlateau()[geo.getDeducY()][geo.getDeducX()].setComposant(TuileComposant.WIFI);
                        case LIGHT -> level.getPlateau()[geo.getDeducY()][geo.getDeducX()].setComposant(TuileComposant.LIGHT);
                        case ENERGY -> level.getPlateau()[geo.getDeducY()][geo.getDeducX()].setComposant(TuileComposant.ENERGY);
                        case ROTATION -> level.getPlateau()[geo.getDeducY()][geo.getDeducX()].rotation();
                    }
                }
            }
            level.propagation();
            level.updateAll();
            repaint();
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
        if(!isGame){
            if(editorSelectionItemJPanel.getItemSelected() == EditeurSelector.DRAW){
                for (Geometrie geo : list) {
                    if (geo.getPolygon().contains(e.getPoint()))
                        tuileCourante = level.getPlateau()[geo.getDeducY()][geo.getDeducX()];
                }
                this.posX = e.getX();
                this.posY = e.getY();
                Graphics2D graphics2D = (Graphics2D) getGraphics();
                graphics2D.setColor(Color.white);
                graphics2D.setStroke(new BasicStroke(15));
                graphics2D.drawLine(posX, posY, posX, posY);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
       // System.out.println(tuileCourante.i + " " + tuileCourante.j);

        if(!isGame) {
            if(editorSelectionItemJPanel.getItemSelected() == EditeurSelector.DRAW) {

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
                    Graphics2D graphics2D = (Graphics2D) getGraphics();
                    graphics2D.setColor(Color.white);
                    graphics2D.setStroke(new BasicStroke(15));
                    graphics2D.drawLine(posX, posY, e.getX(), e.getY());
                    this.posX = e.getX();
                    this.posY = e.getY();
                    level.updateAll();
                    repaint();
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

}