package vue.level;

import controler.Controller;
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

public class LevelSelectedPersoJPanel extends JPanel {

    private ArrayList<BoxLevelJPanel> levelButton;
    private FenetreJFrame parent;
    private JPanel panelLevelSelector;
    private FancyJButton createLevel, niveauDeBase;
    private JScrollPane paneScroll;

    public LevelSelectedPersoJPanel(FenetreJFrame parent) {
        this.setPreferredSize(new Dimension(1280, 720));
        this.setBackground(GraphiqueBuilder.blackBackGround());
        this.createLevel = GraphiqueBuilder.createFancyJbutton("Creer un niveau", e -> {
            parent.addStackPanel(new EditorSelectJPanel(parent));
            parent.update();
        });

        this.niveauDeBase = GraphiqueBuilder.createFancyJbutton("Niveau de base", e -> {
            parent.goBackPanel();
            parent.addStackPanel(new LevelSelectedJPanel(parent));
            parent.update();
        });

        paneScroll = new JScrollPane(panelLevelSelector);
        paneScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        paneScroll.setViewportBorder(BorderFactory.createEmptyBorder());

        this.levelButton = new ArrayList<>();
        this.parent = parent;
        chargeLevel(levelButton);


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
        p.add(niveauDeBase);
        p.add(createLevel);

        this.add(p);
        this.add(panelLevelSelector);
    }

    public void chargeLevel(ArrayList<BoxLevelJPanel> listLevel) {
        for (Level level: Controller.getPlayer().getLevel()){
            listLevel.add(new BoxLevelJPanel(parent, level.getNameLevel(), level));
        }

    }

}
