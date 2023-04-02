package model.typeenum;

public interface DirectionInterface {

    int getSize();

    int getPosition();

    DirectionInterface[] getValues();

    default DirectionInterface rotation() {
        return getValues()[(this.getPosition() + 1) % getSize()];
    }

    int getPosJFromPos(DirectionInterface directionInterface, int column);

}
