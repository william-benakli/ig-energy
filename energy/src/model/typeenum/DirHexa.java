package model.typeenum;

import model.Position;

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
    public Position getPositionIJ(int i, int j){
        int p_i = 0, p_j = 0;
        switch (this){
            case NORD -> {p_i = i-1; p_j = j;}
            case NORD_EST-> {
                if(j%2 == 0){
                    p_i = i-1;
                    p_j = j+1;
                }else{
                    p_i = i;
                    p_j = j+1;
                }
            }
            case SUD_OUEST -> {
                if(j%2 == 0) {
                    p_i = i;
                    p_j = j - 1;
                }else{
                    p_i = i+1;
                    p_j = j - 1;
                }
            }

            case SUD_EST-> {
                if(j%2 == 0){
                    p_i = i; p_j = j+1;
                }else{
                    p_i = i+1; p_j = j+1;
                }
            }
            case NORD_OUEST-> {
                if(j%2 == 0) {
                    p_i = i - 1;
                    p_j = j - 1;
                }else{
                    p_i = i;
                    p_j = j-1;
                }
            }

            case SUD -> {p_i = i+1; p_j = j;}
            default -> {}
        }
        return new Position(p_i, p_j);
    }

}
