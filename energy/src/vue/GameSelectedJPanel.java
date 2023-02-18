package vue;

import vue.utils.GraphiqueBuilder;

import javax.swing.*;

public final class GameSelectedJPanel extends JPanel {

    public GameSelectedJPanel(FenetreJFrame jFrame) {


        this.add(GraphiqueBuilder.createJbutton("Retour", e -> {
            jFrame.goBackPanel();
            jFrame.update();
        }));
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }
}
