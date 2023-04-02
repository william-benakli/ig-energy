package vue.fancycomposant;

import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import java.awt.*;

public class FancyJRadioButton extends JRadioButton {


    public FancyJRadioButton(String imagePath){
        this.setBackground(GraphiqueBuilder.blackBackGround());
        this.setForeground(Color.white);
        this.setIcon(new ImageIcon(imagePath));
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.setPreferredSize(new Dimension(10, 96));
        this.setMinimumSize(new Dimension(10, 19));
        this.setMaximumSize(new Dimension(10, 96));

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(isSelected()){
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.setStroke(new BasicStroke(2));
            graphics2D.drawLine(0,this.getHeight()/2+40, this.getWidth(), this.getHeight()/2+40);
        }
    }
}
