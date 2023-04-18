package vue.editor;

import model.typeenum.EditeurSelector;
import model.typeenum.TuileComposant;
import vue.utils.GraphiqueBuilder;
import vue.utils.ImageEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EditorSelectionItemJPanel extends JPanel {


    private EditeurSelector selected;

    EditorSelectionItemJPanel(){
        this.setLayout(new GridLayout(5,2));
        this.selected = EditeurSelector.DRAW;
        add(new ButtonEditorImage("ressource/icon/stylo_icon.png", EditeurSelector.DRAW));
        add(new ButtonEditorImage("ressource/icon/gomme_icon.png", EditeurSelector.ERASER));
        add(new ButtonEditorImage("ressource/icon/rotation_icon.png", EditeurSelector.ROTATION));
        add(new ButtonEditorImage(ImageEnum.SQUARE_ON_COMPOSANT_ENERGY.getImage(), EditeurSelector.ENERGY));
        add(new ButtonEditorImage(ImageEnum.SQUARE_OFF_COMPOSANT_WIFI.getImage(), EditeurSelector.WIFI));
        add(new ButtonEditorImage(ImageEnum.SQUARE_OFF_COMPOSANT_LAMP.getImage(), EditeurSelector.LIGHT));
        this.setOpaque(false);
        final Dimension dimension = new Dimension(100*3, 100*7);
        this.setPreferredSize(dimension);
        this.setMaximumSize(dimension);
        this.setMinimumSize(dimension);
    }


    public EditeurSelector getItemSelected(){
        return selected;
    }


    private class ButtonEditorImage extends JButton implements MouseListener {

        private Image image;
        private EditeurSelector composant;
        private boolean isHover;

        public ButtonEditorImage(Image image, EditeurSelector composant){
            this.composant = composant;
            this.isHover = false;
            this.image = image;
            this.setBackground(GraphiqueBuilder.blackBackGround());
            this.setBorder(BorderFactory.createLineBorder(Color.white));
            setContentAreaFilled(false);
            setFocusPainted(false);
            this.addMouseListener(this);
        }

        public ButtonEditorImage(String img, EditeurSelector composant){
           this(new ImageIcon(img).getImage(), composant);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(composant == selected) setBorder(BorderFactory.createLineBorder(Color.orange));
            else if(isHover) setBorder(BorderFactory.createLineBorder(Color.lightGray));
            else setBorder(BorderFactory.createLineBorder(Color.white));
            g.drawImage(image, getWidth()/2-50, getHeight()/2-50, this);
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            selected = composant;
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
