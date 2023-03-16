package model.typeenum;

public enum DirCarre implements DirectionInterface {

    NORD(0),
    EST(90),
    SUD(180),
    OUEST(270);

    private int deg;
    private int getDegres() {
        return deg;
    }

    DirCarre(int deg) {
        this.deg = deg;
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
    public int getPosJFromPos(DirectionInterface directionInterface, int column) {
        return 0;
    }
}
