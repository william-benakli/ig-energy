package model;

import java.awt.*;
import java.io.*;

public class ParametersGame implements Serializable {

    private boolean animation;
    private Color backgroundColor, composantColor;

    private static ParametersGame parametersGame = loadParametersGame();

    public static ParametersGame getInstance(){
        if(parametersGame == null){
            return  parametersGame =new ParametersGame(true, new Color(12, 12, 12), Color.white);
        }
        return parametersGame;
    }



    private ParametersGame(boolean animationOn, Color backgroundColor, Color composantColor) {
        this.animation = animationOn;
        this.backgroundColor = backgroundColor;
        this.composantColor = composantColor;
        saveParametersGame(this);
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

    public Color getComposantColor() {
        return composantColor;
    }

    public void setComposantColor(Color backgroundColor) {
        this.composantColor = backgroundColor;
        saveParametersGame(this);
    }

}
