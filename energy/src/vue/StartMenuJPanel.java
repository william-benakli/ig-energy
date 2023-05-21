package vue;

import controler.Controller;
import model.Joueur;
import vue.fancycomposant.FancyJButton;
import vue.level.LevelSelectedJPanel;
import vue.utils.GraphiqueBuilder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class StartMenuJPanel extends JPanel {

    private JPanel buttonJpanel;
    private FancyJButton playButton, settingsButton;


    StartMenuJPanel(FenetreJFrame parent){
        this.setLayout(new GridBagLayout());
        this.buttonJpanel = GraphiqueBuilder.createPanelGrid(4, 1, false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        this.setPreferredSize(new Dimension(1280, 720));
        this.setBackground(GraphiqueBuilder.blackBackGround());
        JTextField userNameField = GraphiqueBuilder.createFancyJTextField("Username");
        if(Controller.getPlayer()!=null)userNameField.setText(Controller.getPlayer().getName());
        buttonJpanel.add(userNameField);
        this.playButton = GraphiqueBuilder.createFancyJbutton("Jouer", "ressource/icon/play_game.png", e -> {
            Joueur existingPlayer = Joueur.load(userNameField.getText());
            if(existingPlayer == null) {
                Joueur newPlayer = new Joueur(userNameField.getText());
                newPlayer.save();
                Controller.setPlayer(newPlayer);
            } else Controller.setPlayer(existingPlayer);

            parent.addStackPanel(new LevelSelectedJPanel(parent));
            parent.update();
        });
        this.settingsButton = GraphiqueBuilder.createFancyJbutton("Paramètres", "ressource/icon/settings_icon.png", e -> {
            parent.addStackPanel(new SettingsJPanel(parent));
            parent.update();
        });
        gbc.weighty = 1;
        buttonJpanel.add(playButton);
        buttonJpanel.add(settingsButton);
        this.add(buttonJpanel, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
