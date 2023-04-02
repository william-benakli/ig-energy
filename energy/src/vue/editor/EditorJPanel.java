package vue.editor;

import model.Level;
import vue.FenetreJFrame;
import vue.GameJPanel;
import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import java.awt.*;

public class EditorJPanel extends JPanel {



    public EditorJPanel(FenetreJFrame parent, Level level){
        this.setBackground(new Color(12, 12, 12));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        final JPanel panelDivision = GraphiqueBuilder.createpanelBoxLayoutOpaque(false, BoxLayout.X_AXIS);
        this.add(GraphiqueBuilder.createFancyJLabel("Editeur de niveau", Color.white, 50));
        this.add(panelDivision);
        panelDivision.add(new EditorSelectionItemJPanel());
        panelDivision.add(new GameJPanel(parent, level));
    }




}
