package vue;

import model.Level;
import model.Tuile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.util.Arrays;

public final class MenuJPanel extends JPanel {

    /**
     *
     */

    Level level;

    public MenuJPanel(FenetreJFrame jFrame, Level level) {
        this.level = level;
        setPreferredSize(new Dimension(level.getHeight()*120, level.getWidth()*120));
    }

    @Override
    public void paintComponent(Graphics g) {

        int height = getSize().width / (level.getHeight());
        int width = getSize().height / (level.getWidth());

        int size = Math.min(width, height);

        for (int row = 0; row < level.getHeight(); row++) {
            for (int col = 0; col < level.getWidth(); col++) {
                g.drawImage(
                        level.getPlateau()[row][col].getImage(),
                        row * size + (getSize().height - ((level.getWidth()) * size)) / 2,
                        col * size + (getSize().width - ((level.getHeight()) * size)) / 2,
                        size,
                        size,
                        this
                );
            }
        }

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
