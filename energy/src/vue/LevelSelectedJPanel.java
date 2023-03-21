package vue;

import main.MainEnergy;
import model.Level;
import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;


public final class LevelSelectedJPanel extends JPanel {


    private JLabel pseudo;
    private ArrayList<JButton> levelButton;
    private FenetreJFrame parent;

    public LevelSelectedJPanel(FenetreJFrame parent, String pseudoname) {

        this.pseudo = new JLabel("Bienvenue dans energy " + pseudoname);
        this.add(pseudo);
        this.levelButton = new ArrayList<>();
        this.parent =parent;
        this.add(GraphiqueBuilder.createJbutton("Retour", e -> {
            parent.goBackPanel();
            parent.update();
        }));
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        try {
            chargeLevel("ressource/level");
        } catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable");
        }
        for(JButton buttons: levelButton){
            this.add(buttons);
        }
    }

    public void chargeLevel(String cheminLevel) throws FileNotFoundException {
        File dir = new File(cheminLevel);
        if(!dir.exists()) throw new FileNotFoundException();
        File[] liste = dir.listFiles();
        for(File item : liste){
            if(item.isFile()){
                JButton button = new JButton(item.getName().replace(".nrg", ""));
                levelButton.add(button);
                button.addActionListener(actionEvent -> {
                    Level niveau = null;
                    try {
                        System.out.println(item.getName());
                        niveau = MainEnergy.parseLineToLevel("ressource/level/" + item.getName());
                        parent.addStackPanel(new MenuJPanel(parent, niveau));
                        parent.update();
                    } catch (FileNotFoundException | ParseException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }



}
