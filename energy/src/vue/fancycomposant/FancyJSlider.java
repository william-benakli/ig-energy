package vue.fancycomposant;

import vue.utils.GraphiqueBuilder;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

public class FancyJSlider extends JSlider {

    public FancyJSlider(int value, int min, int max){
        this.setMaximum(max);
        this.setMinimum(min);
        this.setValue(value);
        this.setForeground(GraphiqueBuilder.composantColor());
        this.setPaintLabels(true);
        this.setPaintTicks(true);
        this.setPaintTrack(true);
        this.setBackground(GraphiqueBuilder.blackBackGround());
    }


    @Override
    public void updateUI() {
        setUI(new FancyJSliderUI(this));
    }

    /*
         Tuto suivi pour faire un FancyJSliderUI
         https://youtu.be/PfOJKiZ5IU4
     */

    private static class FancyJSliderUI extends BasicSliderUI{

        FancyJSliderUI(JSlider jslider){
            super(jslider);
        }


        @Override
        protected Dimension getThumbSize() {
            return new Dimension(30, 30);
        }

        @Override
        public void paintThumb(Graphics g) {
            Graphics2D graphics2D = (Graphics2D) g.create();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.translate(thumbRect.x, thumbRect.y);
            graphics2D.fill(new Ellipse2D.Double(0, 0, thumbRect.width, thumbRect.height));
            graphics2D.dispose();
         }

        @Override
        public void paintTrack(Graphics grphcs) {
            Graphics2D g2 = (Graphics2D) grphcs.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(GraphiqueBuilder.composantColor());
            int size = 35;
            if (slider.getOrientation() == JSlider.HORIZONTAL) {
                int x = 0;
                int y = (trackRect.height - size) / 2;
                g2.fill(new RoundRectangle2D.Double(trackRect.x + x, trackRect.y + y, trackRect.width, size, size, size));
            } else {
                int x = (trackRect.width - size) / 2;
                int y = 0;
                g2.fill(new RoundRectangle2D.Double(trackRect.x + x, trackRect.y + y, size, trackRect.height, size, size));
            }
            g2.dispose();
        }

        @Override
        public void paintTicks(Graphics gr) {
            super.paintTicks(gr);
        }

        @Override
        public void paintFocus(final Graphics g) {

        }
    }
}
