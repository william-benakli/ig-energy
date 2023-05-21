package vue.editor;

import controler.Controller;
import model.Level;
import model.typeenum.TuileShape;
import vue.FenetreJFrame;
import vue.fancycomposant.FancyJButton;
import vue.fancycomposant.FancyJRadioButton;
import vue.fancycomposant.FancyJSlider;
import vue.fancycomposant.FancyJTextField;
import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import java.awt.*;

public class EditorParamsJPanel extends JPanel {


    private final FancyJButton goBack;
    private final JPanel buttonJpanel;
    private final FancyJRadioButton carrejRadio, hexajRadio;
    private final FancyJSlider colonnesJslider,lignesJslider;
    private final JLabel tileParam, typeTuile, lenghtLignes, lenghtColonnes, colonnes, lignes;
    private final JPanel mainJpanel, panelTypeTuile, panelLongueur, panelLargeur;
    private final FancyJTextField nom_level;


    public EditorParamsJPanel(FenetreJFrame parent){
        this.setLayout(new GridBagLayout());
        this.colonnesJslider = new FancyJSlider(4, 1, 12);
        this.lignesJslider = new FancyJSlider(3, 1, 12);
        this.mainJpanel = GraphiqueBuilder.createPanelGrid(6, 1, false);
        this.panelLongueur = GraphiqueBuilder.createPanelGrid(1, 3, false);
        this.panelLargeur = GraphiqueBuilder.createPanelGrid(1, 3, false);
        this.panelTypeTuile = GraphiqueBuilder.createPanelGrid(1, 3, false);
        this.tileParam = GraphiqueBuilder.createFancyJLabel("Paramètres du plateau", Color.white, 50);
        this.nom_level = GraphiqueBuilder.createFancyJTextField("Nom du niveau");
        this.colonnes = GraphiqueBuilder.createFancyJLabel(String.valueOf(lignesJslider.getValue()), Color.white,  GraphiqueBuilder.getFontRoboto(50f));
        this.lignes = GraphiqueBuilder.createFancyJLabel(String.valueOf(colonnesJslider.getValue()), Color.white, GraphiqueBuilder.getFontRoboto(50f));
        this.typeTuile = GraphiqueBuilder.createFancyJLabel("Type tuile :", Color.white, 50);
        this.lenghtLignes = GraphiqueBuilder.createFancyJLabel("Lignes :", Color.white, 50);
        this.lenghtColonnes = GraphiqueBuilder.createFancyJLabel("Colonnes :", Color.white, 50);

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
        final FancyJButton valider = GraphiqueBuilder.createFancyJbutton("Valider", e -> {
            parent.addStackPanel(new EditorJPanel(parent, new Level(nom_level.getText().trim(), lignesJslider.getValue(), colonnesJslider.getValue(), (hexajRadio.isSelected() ? TuileShape.HEXA : TuileShape.CARRE))));
            parent.update();
            repaint();
        });


        lignesJslider.addChangeListener( e->{
            lignes.setText(String.valueOf(lignesJslider.getValue()));
        });

        colonnesJslider.addChangeListener( e->{
            colonnes.setText(String.valueOf(colonnesJslider.getValue()));
        });

        panelTypeTuile.add(typeTuile);
        panelTypeTuile.add(hexajRadio);
        panelTypeTuile.add(carrejRadio);

        final Dimension dim = new Dimension(50, 120);
        colonnesJslider.setPreferredSize(dim);
        colonnesJslider.setMinimumSize(dim);
        colonnesJslider.setMaximumSize(dim);

        panelLongueur.add(lenghtLignes);
        panelLongueur.add(lignesJslider);
        panelLongueur.add(lignes);

        panelLargeur.add(lenghtColonnes);
        panelLargeur.add(colonnesJslider);
        panelLargeur.add(colonnes);

        buttonJpanel.add(goBack);
        buttonJpanel.add(valider);

        mainJpanel.setBorder(BorderFactory.createEmptyBorder(150, 150, 150, 150));
        tileParam.setHorizontalAlignment(JLabel.CENTER);
        mainJpanel.add(tileParam);
        mainJpanel.add(panelTypeTuile);
        mainJpanel.add(panelLargeur);
        mainJpanel.add(panelLongueur);
        mainJpanel.add(nom_level);
        mainJpanel.add(buttonJpanel);

        this.setPreferredSize(new Dimension(1280, 720));
        this.setBackground(new Color(12, 12, 12));
        this.add(mainJpanel, gbc);
    }
}
