package vue.utils;

import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import java.awt.*;
import java.awt.event.ActionListener;

public final class GraphiqueBuilder {

    /**
     * Cette class est une classe static
     *
     */

    public static JButton createJbutton(String text, ActionListener actionEvent){
        final JButton jButton = new JButton();
        jButton.setText(text);
        jButton.setPreferredSize(new Dimension(200, 75));
        jButton.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1)));
        jButton.addActionListener(actionEvent);
        return jButton;
    }

}
