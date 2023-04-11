package model.typeenum;

public enum DirHexa implements DirectionInterface {

    NORD(0),
    NORD_EST(30),
    SUD_EST(60),
    SUD(90),
    SUD_OUEST(150),
    NORD_OUEST(320);

    private int deg;

    DirHexa(int deg) {
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
            case SUD_EST , NORD_EST -> {return j+1;}
            case SUD_OUEST, NORD_OUEST -> { return j-1;}
            case NORD , SUD -> {return j;}
            default -> {return 0;}
        }
    }

    @Override
    public int getI(int i, int j){
        switch (this){
            case NORD_EST,NORD_OUEST -> {return i -  ((j + 1) % 2); }
            case SUD_EST,SUD_OUEST -> {return i + ((j + 1) % 2); }
            case NORD -> {return i-1;}
            case SUD -> {return i+1;}
            default -> {return 0;}
        }
    }

}
