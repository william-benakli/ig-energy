package vue;

import model.Level;
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

    private Level level;
    private ArrayList<Geometrie> list = new ArrayList<Geometrie>();


    public MenuJPanel(FenetreJFrame jFrame, Level level) {
        this.level = level;
        addMouseListener(this);
        setPreferredSize(new Dimension(level.getWidth() * 120, level.getHeight() * 120));
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.clearRect(0, 0, level.getWidth() * 120, level.getHeight() * 120);
        list.clear();
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
                    int x = row * size + (getSize().width - ((level.getWidth()) * size)) / 2 + (size / 2 * ((int) level.getWidth() / 2)) / 2 - (size / 4) * row;
                    int y = col * size + (getSize().height - ((level.getHeight()) * size)) / 2 + size / 3 - (size / 7) * col;
                    System.out.println(size);
                    if (row % 2 == 0 || col < level.getHeight() - 1) {
                        if (row % 2 == 1) y += (size / 2 - size / 12);

                        Geometrie geometriePolygon = new Geometrie(getHexagon(x, y - size / 12, size), row, col);
                        list.add(geometriePolygon);
                        g.setColor(Color.red);
                        g.drawPolygon(geometriePolygon.getPolygon());

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
            }
        }

    }

    public Polygon getHexagon(int x, int y, int size) {
        Polygon hexagon = new Polygon();

        x += size / 2;
        y += size / 2;

        for (int i = 0; i < 6; i++) {
            hexagon.addPoint(
                    (int) (x + size / 2 * Math.cos(i * 2 * Math.PI / 6D)),
                    (int) (y + size / 2 * Math.sin(i * 2 * Math.PI / 6D))
            );
        }

        return hexagon;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

   /*     int height = getSize().width / (level.getWidth());
        int width = getSize().height / (level.getHeight());

        int size = Math.min(width, height);

        int deducX, deducY;
        deducX = (x - (getSize().width - ((level.getWidth()) * size)) / 2) / size;
        deducY = (y - ((getSize().height - ((level.getHeight()) * size)) / 2)) / size;*/

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

    public class Geometrie {
        private Polygon polygon;
        private int i, j;

        Geometrie(Polygon polygon, int i, int j) {
            this.polygon = polygon;
            this.i = i;
            this.j = j;
        }

        public Polygon getPolygon() {
            return polygon;
        }

        public int getDeducY() {
            return j;
        }

        public int getDeducX() {
            return i;
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
