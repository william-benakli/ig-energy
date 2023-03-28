package model;

import java.awt.*;

public class Geometrie {
    private final Polygon polygon;
    private final int i, j;

    public Geometrie(Polygon polygon, int i, int j) {
        this.polygon = polygon;
        this.i = i;
        this.j = j;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public int getDeducY() {
        return j;
    }

    public int getDeducX() {
        return i;
    }

}
