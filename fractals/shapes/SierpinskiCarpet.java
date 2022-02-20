package fractals.shapes;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class SierpinskiCarpet implements FractalShape {
    @Override
    public int maxDepth() {
        return 10;
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
        drawSquare(g2d, 0, 0, Math.min(width, height), depth);
    }

    private static void drawSquare(Graphics2D g2d, double x, double y, double side, int depth) {
        if (depth == 0) {
            g2d.fill(new Rectangle2D.Double(x, y, side, side));
            return;
        }

        double newSide = side / 3;
        double offset2 = 2 * newSide;
        drawSquare(g2d, x, y, newSide, depth - 1);
        drawSquare(g2d, x + newSide, y, newSide, depth - 1);
        drawSquare(g2d, x + offset2, y, newSide, depth - 1);
        drawSquare(g2d, x, y + newSide, newSide, depth - 1);
        drawSquare(g2d, x + offset2, y + newSide, newSide, depth - 1);
        drawSquare(g2d, x, y + offset2, newSide, depth - 1);
        drawSquare(g2d, x + newSide, y + offset2, newSide, depth - 1);
        drawSquare(g2d, x + offset2, y + offset2, newSide, depth - 1);
    }
}
