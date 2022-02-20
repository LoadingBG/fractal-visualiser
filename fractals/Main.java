package fractals;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeListener;

import fractals.shapes.*;

public class Main {
    private static final FractalShape shape = new SierpinskiCarpet();

    private static final int MAX_DEPTH = shape.maxDepth();
    private static final double HEIGHT;
    static {
        int p = 1;
        while (Math.pow(shape.orientationDivisionCount(), p + 1.0) < 1000) {
            ++p;
        }
        HEIGHT = (Math.pow(shape.orientationDivisionCount(), p) / 10 + 2) * 10;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Fractals");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.getContentPane().setPreferredSize(new Dimension((int) Math.round(HEIGHT * 2), (int) Math.round(HEIGHT)));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setLayout(new GridLayout());

            JPanel controlPanel = new JPanel();
            frame.add(controlPanel);

            Canvas canvas = new Canvas();
            canvas.setBackground(Color.WHITE);
            frame.add(canvas);

            setupShape(controlPanel, canvas);

            frame.setVisible(true);
        });
    }

    private static void setupShape(JPanel controlPanel, Canvas canvas) {
        controlPanel.setLayout(new GridLayout(1 + shape.variableSpecs().length, 1));

        JSlider depthSlider = new JSlider(0, MAX_DEPTH, 0);
        JSlider[] variableSliders = Arrays.stream(shape.variableSpecs())
            .map(spec -> new JSlider(spec.min(), spec.max(), spec.initial()))
            .toArray(JSlider[]::new);

        ChangeListener sliderListener = new SliderChangeListener(canvas, depthSlider, variableSliders, shape);

        depthSlider.setMajorTickSpacing(1);
        depthSlider.setPaintTicks(true);
        depthSlider.setPaintLabels(true);

        depthSlider.addChangeListener(sliderListener);

        JPanel depthPanel = new JPanel();
        depthPanel.add(new JLabel("Depth:"));
        depthPanel.add(depthSlider);
        controlPanel.add(depthPanel);

        for (int i = 0; i < variableSliders.length; ++i) {
            JSlider slider = variableSliders[i];
            SliderSpec spec = shape.variableSpecs()[i];

            slider.setMajorTickSpacing(spec.majorTickSpacing());
            slider.setMinorTickSpacing(spec.minorTickSpacing());
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);

            slider.addChangeListener(sliderListener);

            JPanel variablePanel = new JPanel();
            variablePanel.add(new JLabel(spec.label() + ":"));
            variablePanel.add(slider);
            controlPanel.add(variablePanel);
        }
    }
}
