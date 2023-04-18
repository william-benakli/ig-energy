package model.typeenum;

import model.Position;

public enum DirCarre implements DirectionInterface {

    NORD(0),
    EST(90),
    SUD(180),
    OUEST(270);

    private int deg;

    DirCarre(int deg) {
        this.deg = deg;
    }

    private int getDegres() {
        return deg;
    }

    @Override
    public int getSize() {
        return values().length;
    }

    @Override
    public int getPosition() {
        return ordinal();
    }

    @Override
    public DirectionInterface[] getValues() {
        return values();
    }

    @Override
    public Position getPositionIJ(int i, int j){
        int p_i = 0, p_j = 0;
        switch (this){
            case EST -> { p_i = i; p_j = j+1;}
            case OUEST -> { p_i = i; p_j = j-1;}
            case SUD -> { p_i = i + 1;  p_j = j;}
            case NORD -> {p_i = i-1; p_j = j;}
        }
        return new Position(p_i, p_j);
    }
}
