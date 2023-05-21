package vue.level;

import controler.Controller;
import model.Level;
import model.Parser;
import vue.FenetreJFrame;
import vue.GameJPanel;
import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BoxLevelJPanel extends JPanel implements MouseListener {

    private boolean isHover, isAcces;
    private FenetreJFrame parent;
    private Level level;
    private JLabel timeInfoLabel;
    private String name;

    BoxLevelJPanel(FenetreJFrame parent, String name, Level level, boolean isAcces) {
        this.isAcces = isAcces;
        this.level = level;
        this.parent = parent;
        this.isHover = false;
        this.name = name;
        long tm = Controller.getPlayer().getLevelTime(name);
        long second = (tm / 1000) % 60;
        long minute = (tm / (1000 * 60)) % 60;
        String time = String.format("%02d:%02d", minute, second);
        this.timeInfoLabel = GraphiqueBuilder.createFancyJLabel("<html> " + name +" </br>" + time + " </html>", Color.white, GraphiqueBuilder.getFontRoboto(35f));
        setForeground(Color.white);
        setBackground(GraphiqueBuilder.blackBackGround());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addMouseListener(this);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(timeInfoLabel);
    }

    BoxLevelJPanel(FenetreJFrame parent, String name, Level level){
        this(parent, name, level, true);
    }

    public String getText() {
        return name;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(isAcces){
            Graphics2D graphics2D = (Graphics2D) g.create();
            if(isHover) graphics2D.setColor(Color.yellow);
            else graphics2D.setColor(Color.white);
            graphics2D.drawRoundRect(10, 10, getWidth() - 10, getHeight() - 10, 20, 20);
            graphics2D.dispose();
        }else{
            Graphics2D graphics2D = (Graphics2D) g.create();
            graphics2D.setColor(Color.RED);
            graphics2D.drawRoundRect(10, 10, getWidth() - 10, getHeight() - 10, 20, 20);
            graphics2D.dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if(isAcces){
            parent.addStackPanel(new GameJPanel(parent, level));
            parent.update();
        }
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
