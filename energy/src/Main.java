import vue.FenetreJFrame;
import vue.MenuJPanel;

import javax.swing.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FenetreJFrame();
            }
        });
    }
}
