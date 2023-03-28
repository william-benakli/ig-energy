package vue;

import main.MainEnergy;
import model.Level;
import vue.fancycomposant.FancyJButton;
import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;


public final class LevelSelectedJPanel extends JPanel {

    private JLabel pseudo;
    private ArrayList<JButton> levelButton;
    private FenetreJFrame parent;
    private JPanel panelLevelSelector;
    private FancyJButton createLevel;
    private JScrollPane paneScroll;


    public LevelSelectedJPanel(FenetreJFrame parent, String pseudoname) {
        this.setPreferredSize(new Dimension(1280, 720));
        this.setBackground(new Color(12, 12, 12));
        this.pseudo = new JLabel("Bienvenue dans energy ");
        this.add(pseudo);
        this.createLevel = GraphiqueBuilder.createFancyJbutton("Creer un niveau", null);
        paneScroll = new JScrollPane(panelLevelSelector);
        paneScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        paneScroll.setViewportBorder(BorderFactory.createEmptyBorder());

        this.levelButton = new ArrayList<>();
        this.parent =parent;
        try {
            chargeLevel("ressource/level");
        } catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable");
        }
        this.add(GraphiqueBuilder.createFancyJbutton("Retour", e -> {
            parent.goBackPanel();
            parent.update();
        }));
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.panelLevelSelector = GraphiqueBuilder.createPanelGrid(levelButton.size()/2, 3, new Color(12, 12, 12));
        for(JButton buttons: levelButton) panelLevelSelector.add(buttons);
        panelLevelSelector.setBorder(BorderFactory.createLineBorder(Color.white));
        this.add(panelLevelSelector);
        this.add(createLevel);
    }

    public void chargeLevel(String cheminLevel) throws FileNotFoundException {
        File dir = new File(cheminLevel);
        if(!dir.exists()) throw new FileNotFoundException();
        File[] liste = dir.listFiles();
        for(File item : liste){
            if(item.isFile()){
                FancyJButton button = new FancyJButton(item.getName().replace(".nrg", ""));
                levelButton.add(button);
                button.addActionListener(actionEvent -> {
                    Level niveau = null;
             /*       try {
                        System.out.println(item.getName());
                        niveau = main.MainEnergy.parseLineToLevel("ressource/level/" + item.getName());
                        parent.addStackPanel(new MenuJPanel(parent, niveau));
                        parent.update();
                    } catch (FileNotFoundException | ParseException e) {
                        e.printStackTrace();
                    }
               */ });
            }
        }
    }

}
