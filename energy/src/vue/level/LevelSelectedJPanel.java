package vue.level;

import controler.Controller;
import controler.ControllerMenu;
import model.Level;
import vue.FenetreJFrame;
import vue.editor.EditorSelectJPanel;
import vue.fancycomposant.FancyJButton;
import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public final class LevelSelectedJPanel extends JPanel {

    private ArrayList<BoxLevelJPanel> levelButton;
    private FenetreJFrame parent;
    private JPanel panelLevelSelector;
    private FancyJButton createLevel, niveauPerso;
    private JScrollPane paneScroll;
    private ControllerMenu menu;

    public LevelSelectedJPanel(FenetreJFrame parent) {
        this.setPreferredSize(new Dimension(1280, 720));
        this.menu = new ControllerMenu();
        this.parent = parent;
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
        loadLevel(levelButton);

        this.parent = parent;

        this.panelLevelSelector = GraphiqueBuilder.createPanelGrid(levelButton.size() / 2, 3, GraphiqueBuilder.blackBackGround());
        for (JPanel buttons : levelButton) panelLevelSelector.add(buttons);
        panelLevelSelector.setBorder(BorderFactory.createLineBorder(GraphiqueBuilder.composantColor()));

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel p = GraphiqueBuilder.createPanelGrid(1, 3, GraphiqueBuilder.blackBackGround());
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


    public void loadLevel(ArrayList<BoxLevelJPanel> listLevel) {
        for (int i = 0; i < menu.listLvl.size(); i++) {
            Level level = menu.listLvl.get(i);
            BoxLevelJPanel button;
            if (Controller.getPlayer().getLevelMax() > i){
                button = new BoxLevelJPanel(parent, menu, level.getNameLevel(), level, true);
            } else button = new BoxLevelJPanel(parent, menu, level.getNameLevel(), level, false);
            listLevel.add(button);
        }

    }
}
