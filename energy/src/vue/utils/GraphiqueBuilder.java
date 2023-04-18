package vue.utils;

import vue.fancycomposant.FancyJButton;
import vue.fancycomposant.FancyJTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public final class GraphiqueBuilder {

    /**
     * Cette class est une classe static
     *
     */

    public static FancyJButton createFancyJbutton(String text, ActionListener actionEvent){
        final FancyJButton jButton = new FancyJButton(text);
        jButton.addActionListener(actionEvent);
        return jButton;
    }
    public static FancyJButton createFancyJbutton(String text, String path, ActionListener actionEvent){
        final FancyJButton jButton = new FancyJButton(text, path);
        jButton.addActionListener(actionEvent);
        return jButton;
    }

    public static JTextField createTextField(int border){
        final JTextField textField = new JTextField();
        textField.setBorder(BorderFactory.createBevelBorder(border));
        return textField;
    }

    public static FancyJTextField createFancyJTextField(String placeHolder){
        final FancyJTextField textField = new FancyJTextField(placeHolder);
        return textField;
    }

    public static Font getFontCocoGose(float value) {
        try {
            final Font font = Font.createFont(Font.TRUETYPE_FONT, new File("ressource/fonts/CocogooseLight.ttf")).deriveFont(value);
            return font;
        } catch (FontFormatException | IOException e) {
            System.out.println("Erreur chargement des polices d'écritures");
        }
        return new Font(Font.SANS_SERIF,  Font.BOLD, (int) value);
    }

    public static JPanel createPanelGrid(int rows, int cols) {
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(rows, cols));
        return panel;
    }

    public static JPanel createPanelGrid(int rows, int cols, Color color){
        final JPanel panel = createPanelGrid(rows, cols);
        panel.setBackground(color);
        return panel;
    }

    public static JPanel createPanelGrid(int rows, int cols, boolean opaque) {
        final JPanel panel = new JPanel();
        panel.setOpaque(opaque);
        panel.setLayout(new GridLayout(rows, cols));
        return panel;
    }

    public static Color blackBackGround() {
        return new Color(12, 12, 12);
    }

    public static JLabel createJLabelError(String s) {
        return createFancyJLabel(s, Color.RED, 50);
    }

    public static JLabel createFancyJLabel(String editeur_de_niveau, Color white, int value) {
        final JLabel label = new JLabel(editeur_de_niveau);
        label.setFont(GraphiqueBuilder.getFontCocoGose(value));
        label.setForeground(white);
        return label;
    }

    public static JLabel createFancyJLabel(String editeur_de_niveau, Color white, Font font) {
        final JLabel label = new JLabel(editeur_de_niveau);
        label.setFont(font);
        label.setForeground(white);
        return label;
    }

    public static Font getFontRoboto(float value) {
        try {
            final Font font = Font.createFont(Font.TRUETYPE_FONT, new File("ressource/fonts/Roboto-Thin.ttf")).deriveFont(value);
            return font;
        } catch (FontFormatException | IOException e) {
            System.out.println("Erreur chargement des polices d'écritures");
        }
        return new Font(Font.SANS_SERIF,  Font.BOLD, (int) value);
    }

    public static JPanel createpanelBoxLayoutOpaque(boolean opaque, int TYPE_LAYOUT) {
        final JPanel panel = new JPanel();
        panel.setOpaque(opaque);
        panel.setLayout(new BoxLayout(panel, TYPE_LAYOUT));
        return panel;
    }

    public static void createMessageTop(String msg, JPanel panel) {
        panel.getGraphics().setFont(GraphiqueBuilder.getFontCocoGose(25));
        panel.getGraphics().setColor(new Color(254, 254, 254));
        panel.getGraphics().drawString(msg, 0, 0);//panel.getWidth()/2, panel.getHeight()/2);
        panel.repaint();
        panel.revalidate();
        /*
        java.util.Timer timer = new Timer();
        timer.cancel();
        timer.schedule(new TimerTask() {
            int transparence = 100;
            @Override
            public void run() {
                System.out.println("????");
                panel.getGraphics().setFont(GraphiqueBuilder.getFontCocoGose(25));
                panel.getGraphics().setColor(new Color(254, 254, 254, transparence));
                panel.getGraphics().drawString(msg, 0, 0);//panel.getWidth()/2, panel.getHeight()/2);
                panel.repaint();
                if(transparence <= 0){
                    System.out.println("fin");
                    timer.cancel();
                }
                transparence-=20;
            }
        }, 0, 1000);*/
    }
}
