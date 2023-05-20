package vue;

import controler.Controller;
import model.Joueur;
import model.ParametersGame;
import vue.fancycomposant.FancyJButton;
import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import java.awt.*;

public final class SettingsJPanel extends JPanel {

    ParametersGame params;
    private JButton colorPickerButton;
    private JButton animationButton;

    public SettingsJPanel(FenetreJFrame jFrame) {
        JPanel menu = new JPanel();
        menu.setBackground(new Color(12, 12, 12));

        FancyJButton buttonBack = GraphiqueBuilder.createFancyJbutton("Retour", e -> {
            jFrame.goBackPanel();
            jFrame.update();
        });

        FontMetrics fontMetrics = buttonBack.getFontMetrics(buttonBack.getFont());
        menu.setPreferredSize(new Dimension(jFrame.getWidth(), fontMetrics.getHeight() + 20));
        menu.setMaximumSize(new Dimension(jFrame.getWidth(), fontMetrics.getHeight() + 20));
        menu.setMinimumSize(new Dimension(jFrame.getWidth(), fontMetrics.getHeight() + 20));
        menu.add(buttonBack);

        params=ParametersGame.loadParametersGame();
        if (params==null)params=new ParametersGame(true, Color.black);

        JPanel content = new JPanel();
        content.setOpaque(false);

        setAnimation();
        setPicker();

        JPanel jp = new JPanel();
        jp.setBackground(new Color(12, 12, 12));
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
        jp.setOpaque(false);
        jp.add(colorPickerButton);
        jp.add(animationButton);
        //TODO: border
        jp.setBorder(BorderFactory.createEmptyBorder(jFrame.getHeight()/2 - (fontMetrics.getHeight() + 20), 0, 0, 0));
        content.add(jp, BorderLayout.CENTER);

        this.add(menu);
        this.add(content);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(12, 12, 12));
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

    void setPicker(){
        colorPickerButton = GraphiqueBuilder.createFancyJbutton("Color Picker", e -> {
            Color selectedColor = JColorChooser.showDialog(null, "Choisir une couleur d'arrière plan", params.getBackgroundColor());
            if (selectedColor != null) {
                params.setBackgroundColor(selectedColor);
            }
        });
    }

}
