package controler;

import model.BufferedModel;
import model.Geometrie;
import model.Level;
import model.Tuile;
import model.typeenum.TuileShape;
import vue.BoardViewGame;
import vue.typeenum.DirCarreGraphic;
import vue.typeenum.DirHexaGraphic;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;

public abstract class Controller extends MouseAdapter implements ComponentListener {

    public static File file = new File("ressource/tuiles.png");
    public static File file_start_menu = new File("ressource/img/start_menu_bg.png");

    protected final ArrayList<Geometrie> list;
    protected Level level;
    protected BufferedModel model;
    protected BoardViewGame gameView;
    protected Tuile tuileCourante;

    public Controller(Level level, BufferedModel model, BoardViewGame gameView){
        this.model = model;
        this.level = level;
        this.gameView = gameView;
        this.list = new ArrayList<>();
        gameView.addComponentListener(this);

        /*
               int height = getSize().width / (level.getWidth());
        int width = getSize().height / (level.getHeight());
        int size = Math.min(width, height);

        if (level.getTypeTuilePlateau() == TuileShape.CARRE) DirCarreGraphic.paintComponent(level, getSize().width, getSize().height, size, g, list, this);
        else DirHexaGraphic.paintComponent(level, getSize().width, getSize().height, size, g, list, this);

         */
    }

    final public void activer() {
        gameView.addMouseListener(this);
        gameView.addMouseMotionListener(this);
    }

    final public void desactiver() {
        gameView.removeMouseListener(this);
        gameView.removeMouseMotionListener(this);
    }

    public BufferedModel getModel() {
        return model;
    }
    public BoardViewGame getBoardViewGame() {
        return gameView;
    }

    public Level getLevel(){
        return level;
    }

    public void initGeometrieList(){
        if(list.size() > 0)return;
        int width = getBoardViewGame().getSize().width;
        int height = getBoardViewGame().getSize().height;

        int height_ = getBoardViewGame().getSize().width / (level.getWidth());
        int width_ = getBoardViewGame().getSize().height / (level.getHeight());
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

    @Override
    public void componentResized(ComponentEvent e) {
        list.clear();
        initGeometrieList();
        getBoardViewGame().repaint();
    }

    @Override
    public void componentMoved(ComponentEvent componentEvent) {}

    @Override
    public void componentShown(ComponentEvent componentEvent) {}

    @Override
    public void componentHidden(ComponentEvent componentEvent) {}

}
