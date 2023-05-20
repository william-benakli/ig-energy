package model;

import java.io.*;
import java.util.HashMap;

public final class Joueur implements Serializable {

    private final String name;
    /** Cette Hasmap content l'id du level et le temps passé dessus **/
    private final HashMap<String, Long> progression;

    private long startTime;

    @Override
    public String toString() {
        return "name : "+name+" progression "+progression;
    }

    public Joueur(String name){
        this.name = name;
        this.progression = new HashMap<>();
    }

    public void save() {
        File directory = new File("ressource/user/");
        if (!directory.exists()) directory.mkdirs();
        try (FileOutputStream fileOut = new FileOutputStream("ressource/user/"+name + ".ser");
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(this);
        } catch (Exception e) {
            System.out.println("Impossible d'enregistrer le joueur");
        }
    }

    public static Joueur load(String name) {
        try ( FileInputStream fileIn = new FileInputStream("ressource/user/"+name + ".ser");
              ObjectInputStream objectIn = new ObjectInputStream(fileIn) ) {
            return (Joueur) objectIn.readObject();
        } catch (Exception e) {
            return null;
        }
    }

    public String getName() {
        return this.name;
    }

    private void setLevelTime(String levelId, long time) {
        if (!progression.containsKey(levelId) || time < progression.get(levelId)) {
            progression.put(levelId, time);
        }
    }

    public long getLevelTime(String levelId) {
        return progression.getOrDefault(levelId, 0L);
    }

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void stop(String levelId) {
        setLevelTime(levelId, System.currentTimeMillis() - startTime);
        save();
    }
}
