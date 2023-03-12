package vue;

import model.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public final class MenuJPanel extends JPanel implements MouseListener {

    /**
     *
     */

    Level level;

    public MenuJPanel(FenetreJFrame jFrame, Level level) {
        this.level = level;
        addMouseListener(this);
        setPreferredSize(new Dimension(level.getWidth() * 120, level.getHeight() * 120));
    }

    @Override
    public void paintComponent(Graphics g) {

        int height = getSize().width / (level.getWidth());
        int width = getSize().height / (level.getHeight());

        int size = Math.min(width, height);

        for (int row = 0; row < level.getWidth(); row++) {
            for (int col = 0; col < level.getHeight(); col++) {
                g.drawImage(
                        level.getPlateau()[col][row].getImage(),
                        row * size + (getSize().width - ((level.getWidth()) * size)) / 2,
                        col * size + (getSize().height - ((level.getHeight()) * size)) / 2,
                        size,
                        size,
                        this
                );
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        int height = getSize().width / (level.getWidth());
        int width = getSize().height / (level.getHeight());

        int size = Math.min(width, height);

        int deducX = (x - (getSize().width - ((level.getWidth()) * size)) / 2) / size;
        int deducY = (y - ((getSize().height - ((level.getHeight()) * size)) / 2)) / size;

        level.getPlateau()[deducY][deducX].rotation();
        repaint();
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

    /*class CaseJPanel extends JPanel implements MouseListener {

        private Tuile tuile;

        CaseJPanel(Tuile tuile){
            this.tuile = tuile;

            this.setPreferredSize(new Dimension(120, 120));
            this.setMaximumSize(new Dimension(120, 120));
            this.setMinimumSize(new Dimension(120, 120));
            repaint();
            addMouseListener(this);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(tuile.getImage(), 0, 0, this);
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            System.out.println("tuile " + tuile.toString());
            tuile.rotation();
            tuile.update();
            repaint();
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    }*/
}
