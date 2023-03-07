package model.typeenum;

public interface DirectionInterface {

    default void nextRotation(){
        //forcer roation²²
    }

    default boolean compareDir(DirectionInterface directionInterface){
        return directionInterface == this;
    }
}
