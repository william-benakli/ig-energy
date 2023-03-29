package vue.editor;

import model.Level;
import model.typeenum.TuileShape;
import vue.FenetreJFrame;
import vue.fancycomposant.FancyJButton;
import vue.fancycomposant.FancyJRadioButton;
import vue.fancycomposant.FancyJSlider;
import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import java.awt.*;

public class EditorParamsJPanel extends JPanel {


    private FancyJButton valider, goBack;
    private JPanel buttonJpanel;
    private FancyJRadioButton carrejRadio, hexajRadio;
    private FancyJSlider longueurJslider,largerJslider;
    private JLabel typeTuile, lenghtL, lenghtl, longueur, largeur;
    private JPanel mainJpanel, panelTypeTuile, panelLongueur, panelLargeur;


    public EditorParamsJPanel(FenetreJFrame parent){
        this.setLayout(new GridBagLayout());
        this.longueurJslider = new FancyJSlider(4, 1, 10);
        this.largerJslider = new FancyJSlider(3, 1, 10);
        this.mainJpanel = GraphiqueBuilder.createPanelGrid(4, 1, false);
        this.panelLongueur = GraphiqueBuilder.createPanelGrid(1, 3, false);
        this.panelLargeur = GraphiqueBuilder.createPanelGrid(1, 3, false);
        this.panelTypeTuile = GraphiqueBuilder.createPanelGrid(1, 3, false);

        this.longueur = GraphiqueBuilder.createFancyJLabel(String.valueOf(longueurJslider.getValue()), Color.white,  GraphiqueBuilder.getFontRoboto(50f));
        this.largeur = GraphiqueBuilder.createFancyJLabel(String.valueOf(largerJslider.getValue()), Color.white, GraphiqueBuilder.getFontRoboto(50f));
        this.typeTuile = GraphiqueBuilder.createFancyJLabel("Type tuile :", Color.white, 50);
        this.lenghtL = GraphiqueBuilder.createFancyJLabel("Longueur :", Color.white, 50);
        this.lenghtl = GraphiqueBuilder.createFancyJLabel("Largeur :", Color.white, 50);

        this.buttonJpanel = GraphiqueBuilder.createPanelGrid(1, 2, false);
        final ButtonGroup buttonGroup = new ButtonGroup();
        this.carrejRadio = new FancyJRadioButton("ressource/icon/carre_icon.png");
        this.hexajRadio = new FancyJRadioButton("ressource/icon/hexa_icon.png");
        buttonGroup.add(carrejRadio);
        buttonGroup.add(hexajRadio);
        carrejRadio.setSelected(true);
        this.goBack = GraphiqueBuilder.createFancyJbutton("Retour", e->{parent.goBackPanel(); parent.update();});
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.weighty = 1;
        this.valider = GraphiqueBuilder.createFancyJbutton("Valider", e->{
            parent.addStackPanel(new EditorJPanel(parent, new Level(largerJslider.getValue(), longueurJslider.getValue(), (hexajRadio.isSelected() ? TuileShape.HEXA :TuileShape.CARRE))));
            parent.update();
            repaint();
        });


        largerJslider.addChangeListener( e->{
            largeur.setText(String.valueOf(largerJslider.getValue()));
        });

        longueurJslider.addChangeListener( e->{
            longueur.setText(String.valueOf(longueurJslider.getValue()));
        });

        panelTypeTuile.add(typeTuile);
        panelTypeTuile.add(hexajRadio);
        panelTypeTuile.add(carrejRadio);

        largerJslider.setPreferredSize(new Dimension(50, 120));
        largerJslider.setMinimumSize(new Dimension(50, 120));
        largerJslider.setMaximumSize(new Dimension(50, 120));

        panelLongueur.add(lenghtl);
        panelLongueur.add(largerJslider);
        panelLongueur.add(longueur);

        panelLargeur.add(lenghtL);
        panelLargeur.add(longueurJslider);
        panelLargeur.add(largeur);


        buttonJpanel.add(valider);
        buttonJpanel.add(goBack);


        mainJpanel.setBorder(BorderFactory.createEmptyBorder(150, 150, 150, 150));
        mainJpanel.add(panelTypeTuile);
        mainJpanel.add(panelLargeur);
        mainJpanel.add(panelLongueur);
        mainJpanel.add(buttonJpanel);

        this.setPreferredSize(new Dimension(1280, 720));
        this.setBackground(new Color(12, 12, 12));
        this.add(mainJpanel, gbc);
    }
}
