package vue;

import model.Level;
import model.Tuile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public final class MenuJPanel extends JPanel {

    /**
     *
     *
     */

    Level level;
    public MenuJPanel(FenetreJFrame jFrame, Level level){
        this.level = level;

        /*this.add(GraphiqueBuilder.createJbutton("Jouer", e ->{
            jFrame.addStackPanel(new GameSelectedJPanel(jFrame));
            jFrame.update();
        }));
        this.add(GraphiqueBuilder.createJbutton("Parametre", e ->{
            jFrame.addStackPanel(new SettingsJPanel(jFrame));
            jFrame.update();
        }));*/
    //    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //  setAlignmentX(Component.CENTER_ALIGNMENT);

     //   add(new GameJPanel(level));
        this.setBackground(Color.BLACK);

        setLayout(new GridLayout(level.getWeight(), level.getHeight()));
        for (int i = 0; i < level.getWeight(); i++) {
            for (int j = 0; j < level.getHeight(); j++) {
             //   System.out.println("on passe ici");
                add(new CaseJPanel(level.getPlateau().get(i).get(j)));
            }
        }

    }

    class CaseJPanel extends JPanel implements MouseListener {

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
            tuile.rotate();
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
    }
}
