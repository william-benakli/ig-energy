package vue.editor;

import controler.Controller;
import model.Level;
import model.Parser;
import vue.FenetreJFrame;
import vue.fancycomposant.FancyJButton;
import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;

public final class EditorSelectJPanel extends JPanel {


    private FancyJButton chargerButton, editorButton, goBack;
    private JPanel buttonJpanel;
    private JLabel label;

    public EditorSelectJPanel(FenetreJFrame parent){
        this.setLayout(new GridBagLayout());
        this.buttonJpanel = GraphiqueBuilder.createPanelGrid(4, 1, false);
        this.label = GraphiqueBuilder.createJLabelError("");

        this.chargerButton = GraphiqueBuilder.createFancyJbutton("Charger un niveau", e->{
            JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory().toString());
            jFileChooser.setFileFilter(new FileNameExtensionFilter("nrg file only", "nrg"));
            int res = jFileChooser.showOpenDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {
                File file = jFileChooser.getSelectedFile();
                Parser p = new Parser();
                try {
                    Level level = p.parseLineToLevel(file.getPath());
                    parent.addStackPanel(new EditorJPanel(parent, level));
                    parent.update();
                    label.setText("");
                } catch (FileNotFoundException | ParseException ex) {
                    System.out.println("Une erreur est survenue lors du chargement du niveau ressayez");
                    label.setText("Une erreur est survenue, ressayez");
                    repaint();
                }
            }
        });
        this.editorButton = GraphiqueBuilder.createFancyJbutton("Nouveau plateau", e->{
            parent.addStackPanel(new EditorParamsJPanel(parent));
            parent.update();
        });
        this.goBack = GraphiqueBuilder.createFancyJbutton("Retour", e->{parent.goBackPanel(); parent.update();});
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        this.setPreferredSize(new Dimension(1280, 720));
        this.setBackground(new Color(12, 12, 12));
        gbc.weighty = 1;
        buttonJpanel.add(label);
        buttonJpanel.add(editorButton);
        buttonJpanel.add(chargerButton);
        buttonJpanel.add(goBack);
        this.add(buttonJpanel, gbc);
    }
}
