package vue;

import model.*;

import utils.Observer;
import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import java.awt.*;

public class BoardViewGame extends JPanel implements Observer {

    private BufferedModel model;

    public BoardViewGame(int weight, int height) {
        this.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        this.setBackground(GraphiqueBuilder.blackBackGround());
        setPreferredSize(new Dimension(weight, height));
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(model, 0, 0, this);
    }

    @Override
    public void update(BufferedModel model) {
        this.model = model;
        repaint();
    }
}