package vue;

import model.Level;
import model.Tuile;

import javax.swing.*;
import java.awt.*;

public class LevelJPanel extends JPanel {

    private Level level;

    public LevelJPanel(Level level){
        this.level = level;
        this.setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        for(int i = 0; i < level.getWeight(); i ++){
            for(int j = 0; j < level.getHeight(); j ++){
                g.drawImage(level.getPlateau()[i][j].getImage(), i*120, j*120, this);
            }
        }
    }
}
