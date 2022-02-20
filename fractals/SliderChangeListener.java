package fractals;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Arrays;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fractals.shapes.FractalShape;

public class SliderChangeListener implements ChangeListener {
    private final Canvas canvas;
    private final JSlider depthSlider;
    private final JSlider[] variableSliders;
    private final FractalShape shape;

    public SliderChangeListener(Canvas canvas, JSlider depthSlider, JSlider[] variableSliders, FractalShape shape) {
        this.canvas = canvas;
        this.depthSlider = depthSlider;
        this.variableSliders = variableSliders;
        this.shape = shape;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Graphics2D g2d = (Graphics2D) canvas.getGraphics().create();
        g2d.setColor(canvas.getBackground());
        g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke((depthSlider.getMaximum() - depthSlider.getValue()) / 2.0f));

        double width = canvas.getWidth();
        double height = canvas.getHeight();
        double insetFactor = Math.min(width, height) / 10;
        g2d.translate(width / insetFactor, height / insetFactor);
        shape.update(
            g2d,
            (int) Math.round(width * (insetFactor - 2) / insetFactor),
            (int) Math.round(height * (insetFactor - 2) / insetFactor),
            depthSlider.getValue(),
            Arrays.stream(variableSliders).mapToInt(JSlider::getValue).toArray()
        );
    }
}
