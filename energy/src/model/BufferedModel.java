package model;

import utils.Observable;
import utils.Observer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BufferedModel extends BufferedImage implements Observable {

    private Graphics g;
    private ArrayList<Observer> listObserver;

    public BufferedModel(int width, int height, int imageType) {
        super(width, height, imageType);
        this.g = super.getGraphics();
        this.listObserver = new ArrayList<>();
    }

    @Override
    public void subscribe(Observer observer) {
        listObserver.add(observer);
    }

    @Override
    public void notifyObserver() {
        for(Observer item : listObserver) item.update(this);
    }

    public Graphics2D getGraphics() {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        return g2d;
    }

}
