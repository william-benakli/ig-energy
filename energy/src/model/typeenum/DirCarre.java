package model.typeenum;

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
    public int getJ(int i, int j){
        switch (this){
            case NORD, SUD -> {return j;}
            case EST -> {return j+1;}
            case OUEST-> { return j-1;}
            default -> {return 0;}
        }
    }

    @Override
    public int getI(int i, int j){
        switch (this){
            case EST, OUEST -> {return i;}
            case NORD -> {return i-1;}
            case SUD-> { return i+1;}
            default -> {return 0;}
        }
    }
}
