package model;

import model.typeenum.TuileComposant;

public final class TuileHexa extends Tuile{

    private int[] connexion;

    public TuileHexa(TuileComposant typeTuile, int... pos) {
        this.typeTuile = typeTuile;
        connexion = new int[5];
        for (int i:pos) {
            connexion[i] = 1;
        }
    }
}
