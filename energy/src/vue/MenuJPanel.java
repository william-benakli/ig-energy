package vue;

import model.Level;
import model.typeenum.DirCarre;
import model.typeenum.DirHexa;
import model.typeenum.TuileShape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public final class MenuJPanel extends JPanel implements MouseListener {

    /**
     *
     */

    private final Level level;
    private final ArrayList<Geometrie> list = new ArrayList<>();


    public MenuJPanel(FenetreJFrame jFrame, Level level) {
        this.level = level;
        addMouseListener(this);
        setPreferredSize(new Dimension(level.getWidth() * 120, level.getHeight() * 120));
    }

    @Override
    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, getSize().width, getSize().height);
        list.clear();

        int height = getSize().width / (level.getWidth());
        int width = getSize().height / (level.getHeight());
        int size = Math.min(width, height);

        if (level.getTypeTuilePlateau() == TuileShape.CARRE)
            DirCarre.paintComponent(level, getSize().width, getSize().height, size, g, list, this);
        else
            DirHexa.paintComponent(level, getSize().width, getSize().height, size, g, list, this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (Geometrie geo : list) {
            if (geo.getPolygon().contains(e.getPoint())) {
                System.out.println("partout");
                level.getPlateau()[geo.getDeducY()][geo.getDeducX()].rotation();
                repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
