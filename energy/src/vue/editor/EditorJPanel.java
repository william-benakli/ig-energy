package vue.editor;

import controler.Controller;
import controler.ControllerEditBoard;
import model.BufferedModel;
import model.Composer;
import model.Level;
import vue.BoardViewGame;
import vue.FenetreJFrame;
import vue.LevelSelectedJPanel;
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
        BufferedModel model = new BufferedModel(1200, 1200, BufferedImage.TYPE_INT_RGB);
        BoardViewGame boardViewGame = new BoardViewGame(level);
        model.subscribe(boardViewGame);
        model.notifyObserver();

        ControllerEditBoard controllerEditBoard = new ControllerEditBoard(level, model, boardViewGame);
        editorSelectionItemJPanel = new EditorSelectionItemJPanel(controllerEditBoard);
        panelDivision.add(editorSelectionItemJPanel);
        panelDivision.add(boardViewGame);
    }


    public EditorSelectionItemJPanel getEditorSelectionItemJPanel() {
        return editorSelectionItemJPanel;
    }
}
