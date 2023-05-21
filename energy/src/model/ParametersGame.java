package model;

import java.awt.*;
import java.io.*;

public class ParametersGame implements Serializable {

    private boolean animation;
    private Color backgroundColor;

    public ParametersGame(boolean animationOn, Color backgroundColor) {
        this.animation = animationOn;
        this.backgroundColor = backgroundColor;
    }

    public static void saveParametersGame(ParametersGame parameters) {
        File directory = new File("ressource/params/");
        if (!directory.exists()) directory.mkdirs();
        try (FileOutputStream fileOut = new FileOutputStream("ressource/params/params.ser");
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(parameters);
        } catch (IOException e) {
            System.out.println("Impossible d'enregistrer les paramètres: " + e.getMessage());
        }
    }

    public static ParametersGame loadParametersGame() {
        try (FileInputStream fileIn = new FileInputStream("ressource/params/params.ser");
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            return (ParametersGame) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    public boolean isAnimationOn() {
        return animation;
    }

    public void setAnimationOn(boolean animationOn) {
        this.animation = animationOn;
        saveParametersGame(this);
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        saveParametersGame(this);
    }

}
