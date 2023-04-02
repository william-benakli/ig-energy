package model;

import java.io.Serializable;
import java.util.HashMap;

public final class Joueur implements Serializable {

    private String name;
    /** Cette Hasmap content l'id du level et le temps passé dessus **/
    private HashMap<Integer, Long> progression;


    public Joueur(String name){
        this.name = name;
        this.progression = new HashMap<>();
    }

    public String getName() {
        return this.name;
    }
}
