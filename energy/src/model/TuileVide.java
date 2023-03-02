package model;

import model.typeenum.TuileComposant;
import model.typeenum.TuileShape;

import java.util.ArrayList;

public class TuileVide extends Tuile{

    public TuileVide(){
        this.typeTuile = TuileComposant.AUCUN;
        this.finalImage = new ConstructorBufferedTuile(TuileShape.CARRE, TuileComposant.AUCUN, new ArrayList<Integer>());
    }

    @Override
    public void rotate() {

    }

    @Override
    public ArrayList<Integer> getListConnexion() {
        return null;
    }
}
