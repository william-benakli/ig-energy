package vue.editor;

import controler.*;
import model.typeenum.EditeurSelector;
import vue.utils.GraphiqueBuilder;
import vue.utils.ImageEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EditorSelectionItemJPanel extends JPanel {


    private EditeurSelector selected;
    private Controller courantController;

    EditorSelectionItemJPanel(Controller controller){
        this.courantController = controller;
        this.setLayout(new GridLayout(5,2));
        this.selected = EditeurSelector.DRAW;
        add(new ButtonEditorImage("ressource/icon/stylo_icon.png", EditeurSelector.DRAW, new ControllerEditPaintBoard(courantController.getLevel(), courantController.getModel(), courantController.getBoardViewGame())));
        add(new ButtonEditorImage("ressource/icon/gomme_icon.png", EditeurSelector.ERASER, new ControllerEditEraser(courantController.getLevel(), courantController.getModel(), courantController.getBoardViewGame())));
        add(new ButtonEditorImage("ressource/icon/rotation_icon.png", EditeurSelector.ROTATION, new ControllerRotation(courantController.getLevel(), courantController.getModel(), courantController.getBoardViewGame())));
        add(new ButtonEditorImage(ImageEnum.SQUARE_ON_COMPOSANT_ENERGY.getImage(), EditeurSelector.ENERGY, new ControllerEditEnergy(courantController.getLevel(), courantController.getModel(), courantController.getBoardViewGame())));
        add(new ButtonEditorImage(ImageEnum.SQUARE_OFF_COMPOSANT_WIFI.getImage(), EditeurSelector.WIFI, new ControllerEditWifi(courantController.getLevel(), courantController.getModel(), courantController.getBoardViewGame())));
        add(new ButtonEditorImage(ImageEnum.SQUARE_OFF_COMPOSANT_LAMP.getImage(), EditeurSelector.LIGHT, new ControllerEditLight(courantController.getLevel(), courantController.getModel(), courantController.getBoardViewGame())));
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
        private Controller controllerOutils;

        public ButtonEditorImage(Image image, EditeurSelector composant, Controller controllerOutils){
            this.composant = composant;
            this.isHover = false;
            this.image = image;
            this.setBackground(GraphiqueBuilder.blackBackGround());
            this.setBorder(BorderFactory.createLineBorder(Color.white));
            setContentAreaFilled(false);
            setFocusPainted(false);
            this.addMouseListener(this);
            this.controllerOutils = controllerOutils;
        }

        public ButtonEditorImage(String img, EditeurSelector composant, Controller controllerOutils){
           this(new ImageIcon(img).getImage(), composant, controllerOutils);
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
            courantController.desactiver();
            controllerOutils.activer();
            courantController = controllerOutils;
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
