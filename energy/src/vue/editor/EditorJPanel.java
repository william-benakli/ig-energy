package vue.editor;

import controler.Controller;
import controler.ControllerEditPaintBoard;
import model.BufferedModel;
import model.Composer;
import model.Level;
import vue.BoardViewGame;
import vue.FenetreJFrame;
import vue.level.LevelSelectedPersoJPanel;
import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class EditorJPanel extends JPanel {

    private EditorSelectionItemJPanel editorSelectionItemJPanel;

    public EditorJPanel(FenetreJFrame parent, Level level){
        this.setBackground(GraphiqueBuilder.blackBackGround());
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        final JPanel panelDivision = GraphiqueBuilder.createpanelBoxLayoutOpaque(false, BoxLayout.X_AXIS);
        final JPanel panelEditeurEtButton = GraphiqueBuilder.createPanelGrid(1, 2, GraphiqueBuilder.blackBackGround());


        this.add(panelEditeurEtButton);
        this.add(GraphiqueBuilder.createFancyJLabel("Editeur de niveau", GraphiqueBuilder.composantColor(), 50));
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
        listerner(parent, level, panelEditeurEtButton, boardViewGame);

    }

    private void listerner(FenetreJFrame parent, Level level, JPanel panelEditeurEtButton, BoardViewGame boardViewGame) {
        final JButton goback = GraphiqueBuilder.createFancyJbutton("Retour", e -> {
            parent.goBackPanel();
            parent.update();
        });

        final JButton save = GraphiqueBuilder.createFancyJbutton("Sauvegarder", e -> {
            if(level.endGame() && !level.isEmpty()){
                new Composer(level.getNameLevel().trim(), level);
                parent.goBackPanel();
                parent.goBackPanel();
                parent.goBackPanel();
                parent.addStackPanel(new LevelSelectedPersoJPanel(parent));
                parent.update();
            }else {
                boardViewGame.getLabel().disparitionprogressif(5,"Map incomplete impossible d'enrengistrer", GraphiqueBuilder.composantColor(), GraphiqueBuilder.getFontRoboto(54) );
            }

        });
        panelEditeurEtButton.add(goback);
        panelEditeurEtButton.add(save);
    }


}
