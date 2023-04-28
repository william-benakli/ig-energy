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
import java.awt.event.*;
import java.util.ArrayList;

public class BoardViewGame extends JPanel implements MouseListener, MouseMotionListener, ComponentListener {

    private final ArrayList<Geometrie> list;
    private Level level;
    private boolean isGame;
    private int posX,posY;
    private Tuile tuileCourante;
    private EditorSelectionItemJPanel editorSelectionItemJPanel;

    public BoardViewGame(Level level, boolean isGame) {
        this.isGame = isGame;
        this.level = level;
        this.tuileCourante = level.getPlateau()[0][0];
        this.list = new ArrayList<>();

        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 50));
        this.setBackground(GraphiqueBuilder.blackBackGround());
        addMouseListener(this);
        addMouseMotionListener(this);
        addComponentListener(this);
        setPreferredSize(new Dimension(level.getWidth() * 120, level.getHeight() * 120));

    }

    private void initGeometrieList() {
        if(list.size() > 0)return;
        int width = getSize().width;
        int height = getSize().height;

        int height_ = getSize().width / (level.getWidth());
        int width_ = getSize().height / (level.getHeight());
        int size = Math.min(width_, height_);

        for (int row = 0; row < level.getWidth(); row++) {
            for (int col = 0; col < level.getHeight(); col++) {

                if(level.getTypeTuilePlateau() == TuileShape.CARRE) {
                    int x = row * size + (width - ((level.getWidth()) * size)) / 2;
                    int y = col * size + (height - ((level.getHeight()) * size)) / 2;
                    list.add(new Geometrie(DirCarreGraphic.createPolygon(x, y, size), row, col));
                }else {
                    int x = row * size + (width - ((level.getWidth()) * size)) / 2 + (size / 2 * ((int) level.getWidth() / 2)) / 2 - (size / 4) * row;
                    int y = col * size + (height - ((level.getHeight()) * size)) / 2 + size / 3 - (size / 7) * col;
                    if (row % 2 == 0 || col < level.getHeight() - 1) {
                        if (row % 2 == 1) y += (size / 2 - size / 12);
                        list.add(new Geometrie(DirHexaGraphic.createPolygon(x, y - size / 12, size), row, col));
                    }
                }
            }
        }
    }

    public BoardViewGame(Level level, EditorSelectionItemJPanel editorSelectionItemJPanel, boolean isGame) {
        this(level, isGame);
        this.editorSelectionItemJPanel = editorSelectionItemJPanel;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        int height = getSize().width / (level.getWidth());
        int width = getSize().height / (level.getHeight());
        int size = Math.min(width, height);

        if (level.getTypeTuilePlateau() == TuileShape.CARRE) DirCarreGraphic.paintComponent(level, getSize().width, getSize().height, size, g, list, this);
        else DirHexaGraphic.paintComponent(level, getSize().width, getSize().height, size, g, list, this);

    }

    @Override
    public void componentResized(ComponentEvent e) {
        list.clear();
        initGeometrieList();
        repaint();
    }

    @Override
    public void componentMoved(ComponentEvent componentEvent) {}

    @Override
    public void componentShown(ComponentEvent componentEvent) {}

    @Override
    public void componentHidden(ComponentEvent componentEvent) {}

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