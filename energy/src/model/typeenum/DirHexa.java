package model.typeenum;

public enum DirHexa implements DirectionInterface{

    NORD(0),
    NORD_EST(30),
    SUD_EST(60),
    SUD(90),
    SUD_OUEST(150),
    NORD_OUEST(320);

    private int deg;

    private int getDegres(){
        return deg;
    }

    DirHexa(int deg){
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
    public int getPosJFromPos(DirectionInterface directionInterface, int column){
        return 1;
    }

}
