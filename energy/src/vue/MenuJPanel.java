package vue;

import model.Level;
import model.typeenum.TuileShape;

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
        if (level.getTypeTuilePlateau() == TuileShape.CARRE)
            setPreferredSize(new Dimension(level.getWidth() * 120, level.getHeight() * 120));
        else
            setPreferredSize(new Dimension(level.getWidth() * 120, level.getHeight() * 120));
    }

    @Override
    public void paintComponent(Graphics g) {
        int height = getSize().width / (level.getWidth());
        int width = getSize().height / (level.getHeight());
        int size = Math.min(width, height);

        if (level.getTypeTuilePlateau() == TuileShape.CARRE) {
            for (int row = 0; row < level.getWidth(); row++) {
                for (int col = 0; col < level.getHeight(); col++) {
                    int x = row * size + (getSize().width - ((level.getWidth()) * size)) / 2;
                    int y = col * size + (getSize().height - ((level.getHeight()) * size)) / 2;
                    g.drawImage(
                            level.getPlateau()[col][row].getImage(),
                            x,
                            y,
                            size,
                            size,
                            this
                    );
                }
            }
        } else {
            for (int row = 0; row < level.getWidth(); row++) {
                for (int col = 0; col < level.getHeight(); col++) {
                    int x = row * size + (getSize().width - ((level.getWidth()) * size)) / 2 + (size / 2 * ((int) level.getWidth() / 2)) / 2;
                    int y = col * size + (getSize().height - ((level.getHeight()) * size)) / 2 + size / 3;
                    System.out.println(size);
                    if (row % 2 == 0 || col < level.getHeight() - 1) {
                        if (row % 2 == 1) y += (size / 2 - size / 12);
                        g.drawImage(
                                level.getPlateau()[col][row].getImage(),
                                x - (size / 4) * row,
                                y - (size / 7) * col,
                                size,
                                size,
                                this
                        );
                    }
                }
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

        int deducX, deducY;
        if (level.getTypeTuilePlateau()==TuileShape.CARRE){
            deducX = (x - (getSize().width - ((level.getWidth()) * size)) / 2) / size;
            deducY = (y - ((getSize().height - ((level.getHeight()) * size)) / 2)) / size;
        }else{
            //TODO : rotate
            /*int rowX = (x - (getSize().width - ((level.getWidth()) * size)) / 2 + (size / 2 * ((int) level.getWidth() / 2)) / 2) / size;
            int colY = (y - ((getSize().height - ((level.getHeight()) * size)) / 2 + size / 3)) / size;
            System.out.println(" rowX "+rowX+" colY "+colY);
            deducX = (rowX * size + (getSize().width - ((level.getWidth()) * size)) / 2 + (size / 2 * ((int) level.getWidth() / 2)) / 2) - (size / 4) * rowX;
            deducY = (colY * size + (getSize().height - ((level.getHeight()) * size)) / 2 + size / 3) - (size / 7) * colY;*/
            deducX = 0;
            deducY = 0;
        }

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
