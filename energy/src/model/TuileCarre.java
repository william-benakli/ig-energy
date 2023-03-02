package model;

import model.typeenum.TuileComposant;
import model.typeenum.TuileShape;

public final class TuileCarre extends Tuile{

    private int[] connexion;

    public TuileCarre(TuileComposant typeTuile, int... pos) {
        this.typeTuile = typeTuile;
        connexion = new int[4];
        for (int i:pos) {
            connexion[i] = 1;
        }
        this.finalImage = new ConstructorBufferedTuile(TuileShape.CARRE, typeTuile, connexion);
    }


}
