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
        for(Observer item : listObserver) {
            item.update(this);
        }
    }

    public Graphics getGraphics() {
        return this.g;
    }

}
