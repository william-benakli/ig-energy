package vue.utils;

import model.typeenum.DirCarre;
import model.typeenum.DirHexa;
import model.typeenum.TuileComposant;
import model.typeenum.TuileShape;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ConstructorBufferedTuile extends BufferedImage {


    public ConstructorBufferedTuile(TuileShape type, TuileComposant composant, boolean[] edge) {
        super(120, 120, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) super.getGraphics();
        g.setColor(new Color(0, 0, 0, 0));
        g.fillRect(0, 0, 120, 120);
        g.drawImage(type.getImage(), 0, 0, null);


        if (type == TuileShape.CARRE)
            DirCarre.tuileImage(composant, edge, g);
        else if (type == TuileShape.HEXA)
            DirHexa.tuileImage(composant, edge, g);

        g.dispose();

    }
}
