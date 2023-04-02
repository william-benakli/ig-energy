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
    public int getPosJFromPos(DirectionInterface directionInterface, int column) {
       /* switch (directionInterface){
            case NORD ->{ return 1;}
            case SUD -> { return -1;}
            case NORD_OUEST  ->

        }*/
        return 1;
    }

}
