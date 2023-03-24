package vue;

import vue.utils.Behaviour;
import vue.utils.GraphiqueBuilder;
import vue.utils.SimpleMap;

import javax.swing.*;
import java.util.Map;

public final class SettingsJPanel extends JPanel implements SimpleMap<Behaviour, JButton, JPanel> {

    private JButton retour_button;

    public SettingsJPanel(FenetreJFrame jFrame){
        //Fabrique statique
        add(new JSlider());
        retour_button = GraphiqueBuilder.createJbutton("Retour");
        this.add(retour_button);
    }

    @Override
    public JButton getBehaviour(Behaviour key) {
        return retour_button;
    }

    @Override
    public JPanel getNextElement() {
        return null;
    }
}
