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
        this.add(GraphiqueBuilder.createFancyJLabel("Editeur de niveau", Color.white, 50));
        this.add(new GameJPanel(parent, level));
    }

}
