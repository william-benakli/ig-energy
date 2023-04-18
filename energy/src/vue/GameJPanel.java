package vue;

import model.Level;
import vue.fancycomposant.FancyJButton;
import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import java.awt.*;

public final class GameJPanel extends JPanel {

    /**
     *
     */

    private final Level level;
    private final FancyJButton goback;
    private final BoardViewGame boardViewGame;


    public GameJPanel(FenetreJFrame jFrame, Level level) {
        this.level = level;
        this.level.randomised();
        this.goback = GraphiqueBuilder.createFancyJbutton("Retour", e -> {
            jFrame.goBackPanel();
            jFrame.update();
        });
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.boardViewGame = new BoardViewGame(level, true);
        this.setBackground(GraphiqueBuilder.blackBackGround());
        add(goback);
        add(boardViewGame);
        setPreferredSize(new Dimension(level.getWidth() * 120, level.getHeight() * 120));
    }

    public Level getLevel() {
        return level;
    }


}
