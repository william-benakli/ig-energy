package vue;

import vue.utils.Behaviour;
import vue.utils.GraphiqueBuilder;
import vue.utils.SimpleMap;

import javax.swing.*;
import java.awt.*;

public class StartMenuJPanel extends JPanel implements SimpleMap<Behaviour, JButton, JPanel> {

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


    @Override
    public JButton getBehaviour(Behaviour behaviour) {
        if(behaviour == Behaviour.BACK)return settingsButton;
        else return playButton;
    }

    @Override
    public JPanel getNextElement() {
        return null;
    }
}
