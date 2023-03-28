package vue.fancycomposant;

import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class FancyJTextField extends  JTextField {

    public FancyJTextField(String placeHolder){
        this.setFont(GraphiqueBuilder.getFontCocoGose(50f));
        this.setForeground(Color.WHITE);
        this.setText(placeHolder);
        this.setHorizontalAlignment(JTextField.CENTER);
        this.setBackground(new Color(12, 12, 12));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                setText("");
                setForeground(Color.white);
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if(getText().isEmpty()){
                    setForeground(Color.WHITE);
                    setText(placeHolder);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D graphics2d = (Graphics2D) g;
        graphics2d.setStroke(new BasicStroke(4));
        graphics2d.setColor(Color.white);
        graphics2d.drawLine(0,this.getHeight()/2+40, this.getWidth(), this.getHeight()/2+40);
    }
}
