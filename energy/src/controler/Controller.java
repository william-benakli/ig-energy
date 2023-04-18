package controler;

import model.BufferedModel;
import vue.BoardViewGame;

import java.awt.event.MouseAdapter;
import java.io.File;
import java.net.URISyntaxException;
import java.util.Objects;

public abstract class Controller extends MouseAdapter {

    public static File file = new File("ressource/tuiles.png");
    public static File file_start_menu = new File("ressource/img/start_menu_bg.png");

    protected BufferedModel model;
    protected BoardViewGame gameView;
    protected String name;

    public Controller(BufferedModel model, BoardViewGame gameView, String name){
        this.model = model;
        this.gameView = gameView;
        this.name = name;
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

    public BoardViewGame getArdoise() {
        return gameView;
    }

    public String getName() {
        return name;
    }



}
