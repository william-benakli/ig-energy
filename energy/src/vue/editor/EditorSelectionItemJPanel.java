package vue.editor;

import vue.utils.GraphiqueBuilder;
import vue.utils.ImageEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EditorSelectionItemJPanel extends JPanel {


    private int selected;

    EditorSelectionItemJPanel(){
        this.setLayout(new GridLayout(5,2));
        this.selected = 0;
        add(new ButtonEditorImage("ressource/icon/stylo_icon.png"));
        add(new ButtonEditorImage("ressource/icon/gomme_icon.png"));
        add(new JLabel("<html> <br> </html>"));
        add(new JLabel("<html> <br> </html>"));
        //add(new ButtonEditorImage(ImageEnum.SQUARE_ON_COMPOSANT_WIFI.getImage()));
        add(new ButtonEditorImage(ImageEnum.SQUARE_OFF_COMPOSANT_WIFI.getImage()));
        //add(new ButtonEditorImage(ImageEnum.SQUARE_ON_COMPOSANT_LAMP.getImage()));
        add(new ButtonEditorImage(ImageEnum.SQUARE_OFF_COMPOSANT_LAMP.getImage()));
        add(new ButtonEditorImage(ImageEnum.SQUARE_ON_COMPOSANT_ENERGY.getImage()));
        this.setOpaque(false);
        final Dimension dimension = new Dimension(100*3, 100*7);
        this.setPreferredSize(dimension);
        this.setMaximumSize(dimension);
        this.setMinimumSize(dimension);
    }


    public int getItemSelected(){
        return selected;
    }


    private class ButtonEditorImage extends JButton implements MouseListener {

        private Image image;
        private int id;
        private static int idstatic = 0;
        private boolean isHover;

        public ButtonEditorImage(Image image){
            id= idstatic++;
            this.isHover = false;
            this.image = image;
            this.setBackground(GraphiqueBuilder.blackBackGround());
            this.setBorder(BorderFactory.createLineBorder(Color.white));
            setContentAreaFilled(false);
            setFocusPainted(false);
            this.addMouseListener(this);
        }

        public ButtonEditorImage(String img){
           this(new ImageIcon(img).getImage());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(id == selected) setBorder(BorderFactory.createLineBorder(Color.orange));
            else if(isHover) setBorder(BorderFactory.createLineBorder(Color.lightGray));
            else setBorder(BorderFactory.createLineBorder(Color.white));
            g.drawImage(image, getWidth()/2-50, getHeight()/2-50, this);
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            selected = id;
            System.out.println("clicked");
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            this.isHover = true;
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            this.isHover = false;
        }
    }

}
