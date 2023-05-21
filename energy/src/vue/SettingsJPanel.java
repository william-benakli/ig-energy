package vue;

import model.ParametersGame;
import vue.fancycomposant.FancyJButton;
import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public final class SettingsJPanel extends JPanel {

    private final ParametersGame params = ParametersGame.getInstance();
    private JButton colorPickerButton, animationButton, colorPickerComposantButton, deletButton;

    public SettingsJPanel(FenetreJFrame jFrame) {
        JPanel menu = new JPanel();
        menu.setBackground(GraphiqueBuilder.blackBackGround());

        FancyJButton buttonBack = GraphiqueBuilder.createFancyJbutton("Retour et sauvegarder", e -> {
            jFrame.goBackPanel();
            jFrame.goBackPanel();
            jFrame.addStackPanel(new StartMenuJPanel(jFrame));
            jFrame.update();
        });

        FontMetrics fontMetrics = buttonBack.getFontMetrics(buttonBack.getFont());
        menu.setPreferredSize(new Dimension(jFrame.getWidth(), fontMetrics.getHeight() + 20));
        menu.setMaximumSize(new Dimension(jFrame.getWidth(), fontMetrics.getHeight() + 20));
        menu.setMinimumSize(new Dimension(jFrame.getWidth(), fontMetrics.getHeight() + 20));
        menu.add(buttonBack);

        JPanel content = new JPanel();
        content.setOpaque(false);
        setAnimation();
        setPicker();
        setDeletButton();

        JPanel jp = new JPanel();
        jp.setBackground(GraphiqueBuilder.blackBackGround());
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
        jp.setOpaque(false);
        jp.add(colorPickerButton);
        jp.add(colorPickerComposantButton);
        jp.add(animationButton);
        jp.add(deletButton);
        jp.setBorder(BorderFactory.createEmptyBorder(jFrame.getHeight()/2 - (fontMetrics.getHeight() + 20), 0, 0, 0));
        content.add(jp, BorderLayout.CENTER);

        this.add(menu);
        this.add(content);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(GraphiqueBuilder.blackBackGround());
    }

    void setAnimation(){
        animationButton = GraphiqueBuilder.createFancyJbutton("Animation : "+((params.isAnimationOn())?"On":"Off"), e -> {
            if(params.isAnimationOn()){
                params.setAnimationOn(false);
                animationButton.setText("Animation : Off");
            }else{
                params.setAnimationOn(true);
                animationButton.setText("Animation : On");
            }
        });
    }

    void setDeletButton(){
        deletButton = GraphiqueBuilder.createFancyJbutton("Supprimer les maps personalisé", e -> {
            File directory = new File("ressource/level/perso");
            if (directory.exists() && directory.isDirectory()) {
                File[] files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile()) {
                            file.delete();
                        }
                    }
                }
            }
        });
    }

    void setPicker(){
        colorPickerButton = GraphiqueBuilder.createFancyJbutton("Couleur du fond ", e -> {
            Color selectedColor = JColorChooser.showDialog(null, "Choisir une couleur d'arrière plan", params.getBackgroundColor());
            if (selectedColor != null) {
                params.setBackgroundColor(selectedColor);
                repaint();
                revalidate();
                updateUI();
            }
        });

        colorPickerComposantButton = GraphiqueBuilder.createFancyJbutton("Color des composants", e -> {
            Color selectedColor = JColorChooser.showDialog(null, "Choisir une couleur d'arrière plan", params.getBackgroundColor());
            if (selectedColor != null) {
                params.setComposantColor(selectedColor);
                repaint();
                revalidate();
                updateUI();
            }
        });
    }

}
