package vue;

import model.*;

import utils.Observer;
import vue.fancycomposant.FancyJLabel;
import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;

public class BoardViewGame extends JPanel implements Observer {

    private BufferedModel model;
    private FancyJLabel label;

    public BoardViewGame(int weight, int height) {
        this.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        this.setBackground(GraphiqueBuilder.blackBackGround());
        label = new FancyJLabel("", GraphiqueBuilder.getFontRoboto(50f), GraphiqueBuilder.composantColor());
        this.add(label);
        setPreferredSize(new Dimension(weight, height));
        animation();
    }


    public FancyJLabel getLabel(){
        return label;
    }

    private ArrayList<Ellipse2D> bubbles  = new ArrayList<Ellipse2D>();


    private void animation() {
        if(ParametersGame.getInstance().isAnimationOn()){
            Random random = new Random();

            for (int i = 0; i < 150; i++) {
                int x = random.nextInt(1600);
                int y = random.nextInt(1600);
                bubbles.add(new Ellipse2D.Double(x, y, 12, 12));
            }
            new Timer(100, e -> {
                for (Ellipse2D bubble : bubbles) {
                    double dx = (random.nextDouble() - 0.5) * 10;
                    double dy = (random.nextDouble() - 0.5) * 10;
                    bubble.setFrame(bubble.getX() + dx, bubble.getY() + dy, bubble.getWidth(), bubble.getHeight());
                }
                repaint();
                revalidate();
                updateUI();
            }).start();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(model, 0, 0, this);
        if(ParametersGame.getInstance().isAnimationOn()) {
            Graphics2D g2d = (Graphics2D) g;
            for (Ellipse2D bubble : bubbles) {
                g2d.setColor(new Color(GraphiqueBuilder.composantColor().getRed(), GraphiqueBuilder.composantColor().getGreen(), GraphiqueBuilder.composantColor().getBlue(),  30)); // Choisir la couleur des bulles
                g2d.fill(bubble);
            }
        }
    }

    @Override
    public void update(BufferedModel model) {
        this.model = model;
        repaint();
    }
}