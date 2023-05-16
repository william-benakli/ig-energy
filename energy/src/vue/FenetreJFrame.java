package vue;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public final class FenetreJFrame extends JFrame {

    /**
     * FenetreJFrame est la page principal de l'application
     */

    private Stack<JPanel> panelView = new Stack<>();


    //TODO: instance parametres

    public FenetreJFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        panelView.push(new StartMenuJPanel(this));
        setBackground(Color.BLACK);
        update();
        pack();
    }

    public void update(){
        getContentPane().removeAll();
        getContentPane().add(panelView.peek());
        panelView.peek().updateUI();
        panelView.peek().repaint();
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    public void addStackPanel(JPanel panel){
        panelView.push(panel);
    }

    public boolean goBackPanel(){
        if(panelView.size() == 1 )return false;
        panelView.pop();
        return true;
    }
}
