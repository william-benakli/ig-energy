package model;

import model.typeenum.TuileComposant;
import model.typeenum.TuileShape;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public final class TuileCarre extends Tuile{

    private final ArrayList<Integer> listConnexion;

    public TuileCarre(TuileComposant typeTuile, ArrayList<Integer> listConnexion) {
        this.typeTuile = typeTuile;
        this.listConnexion = listConnexion;
        System.out.println(listConnexion.size() + " au debut" + typeTuile.toString());
        this.finalImage = new ConstructorBufferedTuile(TuileShape.CARRE, typeTuile, listConnexion);
    }

    public ArrayList<Integer> getListConnexion() {
        return listConnexion;
    }

    @Override
    public void rotate() {
        System.out.println(this.listConnexion.size() + " taille force ");

        for (int i = 0; i <this.listConnexion.size() ; i++) {
            this.listConnexion.set(i, ((this.listConnexion.get(i)+1)%4));
        }

        this.finalImage = new ConstructorBufferedTuile(TuileShape.CARRE, typeTuile, listConnexion);
    }
}
