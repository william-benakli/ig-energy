package vue;

import vue.utils.GraphiqueBuilder;

import javax.swing.*;

public final class SettingsJPanel extends JPanel {

    public SettingsJPanel(FenetreJFrame jFrame){
        //Fabrique statique
        add(new JSlider());
        this.add(GraphiqueBuilder.createJbutton("Retour", e -> {
            jFrame.goBackPanel();
            jFrame.update();
        }));
    }
}
