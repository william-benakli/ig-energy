package model.typeenum;

public enum DirCarre implements DirectionInterface{

    NORD(0),
    OUEST(90),
    SUD(180),
    EST(270);

    private int deg;

    private int getDegres(){
        return deg;
    }

    DirCarre(int deg){
        this.deg = deg;
    }

}
