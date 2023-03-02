package vue;

import model.Level;
import model.Tuile;
import model.TuileVide;
import model.typeenum.ImageEnum;
import model.typeenum.TuileComposant;
import model.typeenum.TuileShape;
import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

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



    class GameJPanel extends JPanel{

        public GameJPanel(Level level){
            this.setBackground(Color.BLACK);
            setLayout(new GridLayout(level.getWeight(), level.getHeight()));
            for (int i = 0; i < level.getWeight(); i++) {
                for (int j = 0; j < level.getHeight(); j++) {
                 //   System.out.println("on passe ici");
                    add(new CaseJPanel(level.getPlateau().get(i).get(j)));
                }
            }

        }
    }

    class CaseJPanel extends JPanel{

        private Tuile tuile;

        CaseJPanel(Tuile tuile){

            System.out.println(tuile);
            this.tuile = tuile;
            this.setPreferredSize(new Dimension(120, 120));
            this.setMaximumSize(new Dimension(120, 120));
            this.setMinimumSize(new Dimension(120, 120));
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
         //   System.out.println("image on dessine");
            TuileComposant composant = tuile.getComposant();
            TuileShape type = TuileShape.CARRE;

            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 120, 120);

            System.out.println(tuile.getComposant());

            if (composant == TuileComposant.WIFI) {
                System.out.println("WIFI");
                g.drawImage(ImageEnum.SQUARE_ON_COMPOSANT_WIFI.getImage(), 0, 0, this);
            }else if (composant == TuileComposant.ENERGY) {
                System.out.println("ENERGY");
                g.drawImage(ImageEnum.SQUARE_ON_COMPOSANT_ENERGY.getImage(), 0, 0, this);
            } else if (composant == TuileComposant.LIGHT) {
                System.out.println("LIGHT");
                g.drawImage(ImageEnum.SQUARE_OFF_COMPOSANT_LAMP.getImage(), 0, 0, this);
            }
            g.drawImage(type.getImage(), 0, 0, this);
        }
    }
}
