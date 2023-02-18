package vue;

import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import java.awt.*;

public final class MenuJPanel extends JPanel {

    /**
     *
     */

    public MenuJPanel(FenetreJFrame jFrame){
        this.add(GraphiqueBuilder.createJbutton("Jouer", e ->{
            jFrame.addStackPanel(new GameSelectedJPanel(jFrame));
            jFrame.update();
        }));
        this.add(GraphiqueBuilder.createJbutton("Parametre", e ->{
            jFrame.addStackPanel(new SettingsJPanel(jFrame));
            jFrame.update();
        }));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);
    }

}
