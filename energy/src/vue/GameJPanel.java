package vue;

import model.Geometrie;
import model.Level;
import model.typeenum.DirCarre;
import model.typeenum.DirHexa;
import model.typeenum.TuileShape;
import vue.fancycomposant.FancyJButton;
import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public final class GameJPanel extends JPanel {

    /**
     *
     */

    private final Level level;
    private final ArrayList<Geometrie> list = new ArrayList<>();
    private final FancyJButton goback;
    private final BoardViewGame boardViewGame;


    public GameJPanel(FenetreJFrame jFrame, Level level) {
        this.level = level;
        this.level.randomised();
        this.goback = GraphiqueBuilder.createFancyJbutton("Retour", e->{
           jFrame.goBackPanel();
           jFrame.update();
        });
        this.boardViewGame = new BoardViewGame();
        this.setBackground(GraphiqueBuilder.blackBackGround());
        add(goback);
        add(boardViewGame);
        setPreferredSize(new Dimension(level.getWidth() * 120, level.getHeight() * 120));
    }

    public Level getLevel() {
        return level;
    }

    private class BoardViewGame extends JPanel implements MouseListener {


        public BoardViewGame() {
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
                DirCarre.paintComponent(level, getSize().width, getSize().height, size, g, list, GameJPanel.this);
            else
                DirHexa.paintComponent(level, getSize().width, getSize().height, size, g, list, GameJPanel.this);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            for (Geometrie geo : list) {
                if (geo.getPolygon().contains(e.getPoint())) {
                    level.getPlateau()[geo.getDeducY()][geo.getDeducX()].rotation();
                    level.propagation();
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
}
