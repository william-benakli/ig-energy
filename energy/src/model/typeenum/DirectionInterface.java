package model.typeenum;

public interface DirectionInterface {

    int getSize();

    int getPosition();

    DirectionInterface[] getValues();

    default DirectionInterface rotation() {
        return getValues()[(this.getPosition() + 1) % getSize()];
    }

    default int getPosIFromPos(DirectionInterface directionInterface) {
        return 0;
    }

    default int getPosJFromPos(DirectionInterface directionInterface) {
        return 0;
    }

    /**
     *
     * rotation en utilisant les trucs de enum cardinal
     *
     */

}
