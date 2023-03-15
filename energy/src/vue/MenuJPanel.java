package vue;

import model.Level;
import model.typeenum.TuileShape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

public final class MenuJPanel extends JPanel implements MouseListener {

    /**
     *
     */

    private Level level;
    private ArrayList<Geometrie> list = new ArrayList<Geometrie>();


    public class Geometrie{
        private Polygon polygon;
        private int i, j;

        Geometrie(Polygon polygon, int i, int j){
            this.polygon = polygon;
            this.i = i;
            this.j = j;
        }

        public Polygon getPolygon() {
            return polygon;
        }

        public int getDeducY(){
            return j;
        }
        public int getDeducX(){
            return i;
        }

    }
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
                    Geometrie geometriePolygon = new Geometrie(getHexagon(row*size, col*size, size), row, col);
                    list.add(geometriePolygon);
                    g.setColor(Color.red);
                    g.drawPolygon(geometriePolygon.getPolygon());
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

    public Polygon getHexagon(final int x, final int y, int size) {
        Polygon hexagon = new Polygon();
        int side = 60;
        int h = side / 2;
        int w = (int) (side * (Math.sqrt(3) / 2));

        hexagon.addPoint(x+(size/4), y);
        hexagon.addPoint(x+(size*3/4), y);
        hexagon.addPoint(x, y+(size /2));
        hexagon.addPoint(x+size, y+(size/2))    ;
        hexagon.addPoint(x+(size/4), y+size);
        hexagon.addPoint(x, y+size/2);

        /*
        hexagon.addPoint(x, y + h);
        hexagon.addPoint(x + w, y);
        hexagon.addPoint(x + 2 * w, y + h);
        hexagon.addPoint(x + 2 * w, y + (int) (1.5 * side));
        hexagon.addPoint(x + w, y + 2 * side);
        hexagon.addPoint(x, y + (int) (1.5 * side));*/
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

        for(Geometrie geo: list){
            if(geo.getPolygon().contains(e.getPoint())){
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
