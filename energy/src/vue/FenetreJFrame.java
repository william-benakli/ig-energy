package vue;

import model.Level;
import vue.utils.Behaviour;
import vue.utils.SimpleMap;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public final class FenetreJFrame extends JFrame {

    /**
     * FenetreJFrame est la page principal de l'application
     */

    private Stack<SimpleMap<Behaviour, JButton, JPanel>> panelView = new Stack<>();

    public FenetreJFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        panelView.push(new StartMenuJPanel(this));
        setBackground(Color.BLACK);
        update();
        pack();
        ((panelView.peek()).getBehaviour(Behaviour.BACK)).addActionListener(e ->{
            goBackPanel();
            update();
        });
        ((panelView.peek()).getBehaviour(Behaviour.NEXT)).addActionListener(e ->{
            addStackPanel(panelView.peek().getNextElement());
            update();
        });
        
    }

    public void update(){
        getContentPane().removeAll();
        getContentPane().add((JPanel) panelView.peek());
        ((JPanel) panelView.peek()).updateUI();
        getContentPane().revalidate();
    }

    public void addStackPanel(JPanel panel){
        panelView.push((SimpleMap) panel);
    }

    public boolean goBackPanel(){
        if(panelView.size() == 1 )return false;
        panelView.pop();
        return true;
    }
}
