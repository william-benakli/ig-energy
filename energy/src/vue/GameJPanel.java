package vue;

import controler.ControllerInGame;
import controler.ControllerMenu;
import model.BufferedModel;
import model.Level;
import vue.fancycomposant.FancyJButton;
import vue.level.LevelSelectedJPanel;
import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public final class GameJPanel extends JPanel {

    private final Level level;
    private final FancyJButton goback;
    private final BoardViewGame boardViewGame;

    public GameJPanel(FenetreJFrame jFrame, Level level, ControllerMenu menu) {
        this.level = level;
        this.level.randomised();
        this.goback = GraphiqueBuilder.createFancyJbutton("Retour", e -> {
            jFrame.goBackPanel();
            jFrame.goBackPanel();
            jFrame.addStackPanel(new LevelSelectedJPanel(jFrame));
            jFrame.update();
        });

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.boardViewGame = new BoardViewGame(level.getWidth() * 120, level.getHeight() * 120);
        BufferedModel model = new BufferedModel(jFrame.getWidth(), jFrame.getHeight(), BufferedImage.TYPE_INT_RGB);
        model.subscribe(boardViewGame);
        model.notifyObserver();

        ControllerInGame controllerEditBoard = new ControllerInGame(level, model, boardViewGame, menu);
        controllerEditBoard.activer();

        this.setBackground(GraphiqueBuilder.blackBackGround());
        add(goback);
        add(boardViewGame);
        setPreferredSize(new Dimension(level.getWidth() * 120, level.getHeight() * 120));
    }

    public Level getLevel() {
        return level;
    }


}
