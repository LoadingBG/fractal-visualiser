package fractals.shapes;

import java.awt.Graphics2D;

public class CantorSet implements FractalShape {
    @Override
    public int maxDepth() {
        return 7;
    }

    @Override
    public int orientationDivisionCount() {
        return 3;
    }

    @Override
    public SliderSpec[] variableSpecs() {
        return new SliderSpec[0];
    }

    @Override
    public void update(Graphics2D g2d, double width, double height, int depth, int[] sliderVariables) {
        g2d.translate(0, height / 2.0);
        drawLine(g2d, width, depth);
    }

    private static void drawLine(Graphics2D g2d, double len, int depth) {
        if (depth == 0) {
            g2d.drawLine(0, 0, (int) len, 0);
            return;
        }

        drawLine(g2d, len / 3, depth - 1);
        g2d.translate(len * 2 / 3, 0);
        drawLine(g2d, len / 3, depth - 1);
        g2d.translate(-len * 2 / 3, 0);
    }
}
