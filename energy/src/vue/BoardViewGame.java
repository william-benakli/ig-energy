package vue;

import model.*;

import model.typeenum.TuileShape;
import utils.Observer;
import vue.typeenum.DirCarreGraphic;
import vue.typeenum.DirHexaGraphic;
import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import java.awt.*;

public class BoardViewGame extends JPanel implements Observer {

    private BufferedModel model;
    private Level level;

    public BoardViewGame(Level level) {
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 50));
        this.setBackground(GraphiqueBuilder.blackBackGround());
        setPreferredSize(new Dimension(level.getWidth() * 120, level.getHeight() * 120));
        this.level = level;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        g.drawImage(model, 0, 0, this);
    }

    @Override
    public void update(BufferedModel model) {
        this.model = model;
        repaint();
    }
}