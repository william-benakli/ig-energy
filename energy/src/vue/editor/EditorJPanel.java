package vue.editor;

import controler.ControllerEditPaintBoard;
import model.BufferedModel;
import model.Composer;
import model.Level;
import vue.BoardViewGame;
import vue.FenetreJFrame;
import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class EditorJPanel extends JPanel {

    private EditorSelectionItemJPanel editorSelectionItemJPanel;

    public EditorJPanel(FenetreJFrame parent, Level level){
        this.setBackground(new Color(12, 12, 12));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        final JPanel panelDivision = GraphiqueBuilder.createpanelBoxLayoutOpaque(false, BoxLayout.X_AXIS);
        final JPanel panelEditeurEtButton = GraphiqueBuilder.createPanelGrid(1, 2, GraphiqueBuilder.blackBackGround());//   false, BoxLayout.X_AXIS);

        final JButton goback = GraphiqueBuilder.createFancyJbutton("Retour", e -> {
            parent.goBackPanel();
            parent.update();
        });

        final JButton save = GraphiqueBuilder.createFancyJbutton("Sauvegarder", e -> {
            if(level.endGame()){
                new Composer(level.getNameLevel().trim(), level);
                parent.goBackPanel();
                parent.goBackPanel();
                parent.goBackPanel();
                parent.update();
            }else{
                GraphiqueBuilder.createMessageTop("Sauvegarde impossible map non terminé", this);
            }

        });

        panelEditeurEtButton.add(goback);
        panelEditeurEtButton.add(save);

        this.add(panelEditeurEtButton);
        this.add(GraphiqueBuilder.createFancyJLabel("Editeur de niveau", Color.white, 50));
        this.add(panelDivision);
        BoardViewGame boardViewGame = new BoardViewGame(level.getWidth() * 120, level.getHeight() * 120);
        System.out.println(boardViewGame.getSize().width);
        System.out.println(boardViewGame.getSize().height);
        BufferedModel model = new BufferedModel(parent.getWidth(), parent.getWidth(), BufferedImage.TYPE_INT_RGB);
        model.subscribe(boardViewGame);
        model.notifyObserver();
        ControllerEditPaintBoard controllerEditBoard = new ControllerEditPaintBoard(level, model, boardViewGame);
        editorSelectionItemJPanel = new EditorSelectionItemJPanel(controllerEditBoard);
        controllerEditBoard.activer();
        panelDivision.add(editorSelectionItemJPanel);
        panelDivision.add(boardViewGame);
    }


}
