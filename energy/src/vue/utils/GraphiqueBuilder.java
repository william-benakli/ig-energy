package vue.utils;

import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

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

    public static JTextField createTextField(int border){
        final JTextField textField = new JTextField();
        textField.setBorder(BorderFactory.createBevelBorder(border));
        return textField;
    }

    public static JTextField createTextField(String placeHolder){
        final JTextField textField = new JTextField(placeHolder);
        textField.setForeground(Color.gray);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                textField.setText("");
                textField.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if(textField.getText().isEmpty()){
                    textField.setForeground(Color.gray);
                    textField.setText(placeHolder);
                }
            }
        });
        return textField;
    }
}
