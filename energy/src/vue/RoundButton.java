package vue;

import model.typeenum.ImageEnum;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;
public class RoundButton extends JButton {
    public RoundButton(String label) {
        super(label);
        setBackground(Color.lightGray);
        setFocusable(false);

    /*
     These statements enlarge the button so that it
     becomes a circle rather than an oval.
    */
        setPreferredSize(new Dimension(120, 120));
    /*
     This call causes the JButton not to paint the background.
     This allows us to paint a round background.
    */
        setContentAreaFilled(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.gray);
        } else {
            g.setColor(getBackground());
        }
    //    g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
        g.drawImage(ImageEnum.HEXAGON_OFF_EMPTY.getImage(), 0, 0, this);
        super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
        g.drawImage(ImageEnum.HEXAGON_OFF_EMPTY.getImage(), 0, 0, this);
    }
    // Hit detection.
    Shape shape;
    public boolean contains(int x, int y) {
        // If the button has changed size,  make a new shape object.
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Rounded Button Example");
        JPanel panel = new JPanel();
        JButton b1 = new RoundButton("B1");
        JButton b2 = new RoundButton("B2");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(b1);
        panel.add(b2);
        panel.setSize(300, 150);
        frame.setVisible(true);
        frame.setSize(new Dimension(500,500));
        frame.setContentPane(panel);
      //  frame.pack();
    }
}