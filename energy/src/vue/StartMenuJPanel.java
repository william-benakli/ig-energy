package vue;

import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import java.awt.*;

public class StartMenuJPanel extends JPanel {

    private JTextField userNameField;
    private JButton playButton, settingsButton;

    StartMenuJPanel(FenetreJFrame parent){
        this.setLayout(new GridLayout(3, 1));
        this.userNameField = GraphiqueBuilder.createTextField("Votre pseudo");
        this.playButton = GraphiqueBuilder.createJbutton("Jouer", e -> {
            parent.addStackPanel(new LevelSelectedJPanel(parent, userNameField.getText()));
            parent.update();
        }
        );
        this.settingsButton = GraphiqueBuilder.createJbutton("Paramètres", null);
        this.add(userNameField);
        this.add(playButton);
        this.add(settingsButton);
    }
}
