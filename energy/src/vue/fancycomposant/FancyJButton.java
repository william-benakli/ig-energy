package vue.fancycomposant;

import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FancyJButton extends JButton implements MouseListener {

    private Boolean isHover;
    private Image image;

    public FancyJButton(String text){
        super(text);
        this.isHover = false;
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFont(GraphiqueBuilder.getFontCocoGose(50f));
        setFocusPainted(false);
        setForeground(Color.white);
        addMouseListener(this);
    }

    public FancyJButton(String text, String imagePath){
        super(text);
        this.setIcon(new ImageIcon(imagePath));
        this.isHover = false;
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFont(GraphiqueBuilder.getFontCocoGose(50f));
        setFocusPainted(false);
        setForeground(Color.white);
        addMouseListener(this);
    }


    public boolean isHover(){
        return isHover;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D graphics2d = (Graphics2D) g;
        graphics2d.setStroke(new BasicStroke(12));
        graphics2d.setColor(Color.white);
        if(isHover) graphics2d.drawLine(0,this.getHeight()/2+40, this.getWidth(), this.getHeight()/2+40);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) { this.isHover = false;

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        this.isHover = true;
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        this.isHover = false;
    }

}
