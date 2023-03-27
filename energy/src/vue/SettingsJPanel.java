package vue;

import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import java.awt.*;

public final class SettingsJPanel extends JPanel {

    public SettingsJPanel(FenetreJFrame jFrame){
        //Fabrique statique
        this.setBackground(new Color(12, 12, 12));
        this.add(GraphiqueBuilder.createFancyJbutton("Retour", e -> {
            jFrame.goBackPanel();
            jFrame.update();
        }));
    }
}
