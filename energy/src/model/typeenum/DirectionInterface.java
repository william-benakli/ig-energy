package model.typeenum;

import model.Position;

public interface DirectionInterface {

    int getSize();

    int getPosition();

    DirectionInterface[] getValues();

    default DirectionInterface rotation() {
        return getValues()[(this.getPosition() + 1) % getSize()];
    }

    default int getOpositeDirection(int v){
        return (v+(getValues().length/2))%getValues().length;
    }

    Position getPositionIJ(int i, int j);
}
