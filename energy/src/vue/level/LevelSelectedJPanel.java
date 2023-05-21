package vue.level;

import model.Level;
import model.Parser;
import vue.FenetreJFrame;
import vue.editor.EditorSelectJPanel;
import vue.fancycomposant.FancyJButton;
import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;


public final class LevelSelectedJPanel extends JPanel {

    private ArrayList<BoxLevelJPanel> levelButton;
    private FenetreJFrame parent;
    private JPanel panelLevelSelector;
    private FancyJButton createLevel, niveauPerso;
    private JScrollPane paneScroll;


    public LevelSelectedJPanel(FenetreJFrame parent) {
        this.setPreferredSize(new Dimension(1280, 720));
        this.setBackground(GraphiqueBuilder.blackBackGround());
        this.createLevel = GraphiqueBuilder.createFancyJbutton("Creer un niveau", e -> {
            parent.addStackPanel(new EditorSelectJPanel(parent));
            parent.update();
        });
        this.niveauPerso = GraphiqueBuilder.createFancyJbutton("Mes niveaux ", e -> {
            parent.goBackPanel();
            parent.addStackPanel(new LevelSelectedPersoJPanel(parent));
            parent.update();
        });

        paneScroll = new JScrollPane(panelLevelSelector);
        paneScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        paneScroll.setViewportBorder(BorderFactory.createEmptyBorder());

        this.levelButton = new ArrayList<>();

        this.parent = parent;
        try {
            chargeLevel("ressource/level",levelButton);
            levelButton.sort(Comparator.comparingInt(chiffre -> Integer.parseInt(chiffre.getText().replace("level", ""))));
        } catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable");
        }


        this.panelLevelSelector = GraphiqueBuilder.createPanelGrid(levelButton.size() / 2, 3, new Color(12, 12, 12));
        for (JPanel buttons : levelButton) panelLevelSelector.add(buttons);
        panelLevelSelector.setBorder(BorderFactory.createLineBorder(Color.white));

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel p = GraphiqueBuilder.createPanelGrid(1, 3, new Color(12, 12, 12));
        p.setMaximumSize(new Dimension(
                Integer.MAX_VALUE,
                120
        ));

        p.add(GraphiqueBuilder.createFancyJbutton("Retour", e -> {
            parent.goBackPanel();
            parent.update();
        }));
        p.add(niveauPerso);
        p.add(createLevel);

        this.add(p);
        this.add(panelLevelSelector);

    }

    public void chargeLevel(String cheminLevel, ArrayList<BoxLevelJPanel> listLevel) throws FileNotFoundException {
        File dir = new File(cheminLevel);
        if (!dir.exists()) throw new FileNotFoundException();
        File[] liste = dir.listFiles();
        for (File item : liste) {
            if (item.isFile()) {
                Level level = null;
                try {
                    level = new Parser().parseLineToLevel(cheminLevel + "/" + item.getName());

                    BoxLevelJPanel button = new BoxLevelJPanel(parent, item.getName().replace(".nrg", ""), level);
                    listLevel.add(button);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
