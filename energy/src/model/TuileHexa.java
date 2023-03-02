package model;

import model.typeenum.TuileComposant;

import java.util.ArrayList;

public final class TuileHexa extends Tuile{

    private int[] connexion;

    public TuileHexa(TuileComposant typeTuile, int... pos) {
        this.typeTuile = typeTuile;
        connexion = new int[5];
        for (int i:pos) {
            connexion[i] = 1;
        }
    }

    @Override
    public void rotate() {

    }

    @Override
    public ArrayList<Integer> getListConnexion() {
        return null;
    }
}
