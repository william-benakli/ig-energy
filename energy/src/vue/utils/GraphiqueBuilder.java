package vue.utils;

import model.ParametersGame;
import vue.FenetreJFrame;
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
        return ParametersGame.getInstance().getBackgroundColor();
    }
    public static Color composantColor() {return ParametersGame.getInstance().getComposantColor();}

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

}
