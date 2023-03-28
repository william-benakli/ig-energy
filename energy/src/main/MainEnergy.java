package main;

import vue.FenetreJFrame;

import javax.swing.*;

public class MainEnergy {


    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FenetreJFrame();
            }
        });
    }


}
