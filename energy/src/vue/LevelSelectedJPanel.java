package vue;

import model.Level;
import model.Parser;
import vue.editor.EditorSelectJPanel;
import vue.fancycomposant.FancyJButton;
import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;


public final class LevelSelectedJPanel extends JPanel {

    private ArrayList<JButton> levelButton;
    private ArrayList<JButton> userLevelButton;
    private FenetreJFrame parent;
    private JPanel panelLevelSelector, panelPersoLevelSelector;
    private FancyJButton createLevel;
    private JScrollPane paneScroll, paneScrollPerso;


    public LevelSelectedJPanel(FenetreJFrame parent) {
        this.setPreferredSize(new Dimension(1280, 720));
        this.setBackground(GraphiqueBuilder.blackBackGround());
        this.createLevel = GraphiqueBuilder.createFancyJbutton("Creer un niveau", e -> {
            parent.addStackPanel(new EditorSelectJPanel(parent));
            parent.update();
        });
        paneScroll = new JScrollPane(panelLevelSelector);
        paneScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        paneScroll.setViewportBorder(BorderFactory.createEmptyBorder());


        paneScrollPerso = new JScrollPane(panelPersoLevelSelector);
        paneScrollPerso.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        paneScrollPerso.setViewportBorder(BorderFactory.createEmptyBorder());

        this.levelButton = new ArrayList<>();
        this.userLevelButton = new ArrayList<>();

        this.parent = parent;
        try {
            chargeLevel("ressource/level",levelButton);
            chargeLevel("ressource/level/perso", userLevelButton);
            levelButton.sort(Comparator.comparingInt(chiffre -> Integer.parseInt(chiffre.getText().replace("level", ""))));
        } catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable");
        }


        this.panelLevelSelector = GraphiqueBuilder.createpanelBoxLayoutOpaque(false, BoxLayout.X_AXIS);//GraphiqueBuilder.createPanelGrid(levelButton.size() / 2, 3, new Color(12, 12, 12));
        for (JButton buttons : levelButton) panelLevelSelector.add(buttons);
        panelLevelSelector.setBorder(BorderFactory.createLineBorder(Color.white));

        this.panelPersoLevelSelector = GraphiqueBuilder.createpanelBoxLayoutOpaque(false, BoxLayout.X_AXIS);//GraphiqueBuilder.createPanelGrid(levelButton.size() / 2, 3, new Color(12, 12, 12));
        for (JButton buttons : userLevelButton) panelPersoLevelSelector.add(buttons);
        panelPersoLevelSelector.setBorder(BorderFactory.createLineBorder(Color.white));
        panelPersoLevelSelector.add(new JLabel("Vos niveaux personnalisés: "));
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
        p.add(createLevel);

        this.add(p);
        this.add(panelLevelSelector);
        this.add(panelPersoLevelSelector);


    }

    public void chargeLevel(String cheminLevel, ArrayList<JButton> listLevel) throws FileNotFoundException {
        File dir = new File(cheminLevel);
        if (!dir.exists()) throw new FileNotFoundException();
        File[] liste = dir.listFiles();
        for (File item : liste) {
            if (item.isFile()) {
                BoxLevelJButton button = new BoxLevelJButton(item.getName().replace(".nrg", ""));
                listLevel.add(button);
                button.addActionListener(actionEvent -> {
                    Level niveau = null;
                    try {
                        Parser p = new Parser();
                        niveau = p.parseLineToLevel(cheminLevel + item.getName());
                        parent.addStackPanel(new GameJPanel(parent, niveau));
                        parent.update();
                    } catch (FileNotFoundException | ParseException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }

    private class BoxLevelJButton extends JButton implements MouseListener {

        private boolean isHover;

        BoxLevelJButton(String name) {
            this.isHover = false;
            setBorderPainted(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setFont(GraphiqueBuilder.getFontRoboto(30f));
            setText(name); //"<html> " + name + "<br> Temps:  </html>");
            setForeground(Color.white);
            setBackground(GraphiqueBuilder.blackBackGround());
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            addMouseListener(this);
        }


        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D = (Graphics2D) g.create();
            graphics2D.setColor(Color.white);
            graphics2D.drawRoundRect(10, 10, getWidth() - 10, getHeight() - 10, 20, 20);
            graphics2D.dispose();
        }


        @Override
        public void mouseClicked(MouseEvent mouseEvent) {

        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            setForeground(Color.yellow);
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            setForeground(Color.WHITE);
        }
    }

}
