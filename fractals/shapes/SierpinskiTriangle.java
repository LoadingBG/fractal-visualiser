package fractals.shapes;

import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class SierpinskiTriangle implements FractalShape {
    @Override
    public int maxDepth() {
        return 8;
    }

    @Override
    public int orientationDivisionCount() {
        return 2;
    }

    @Override
    public SliderSpec[] variableSpecs() {
        return new SliderSpec[0];
    }

    @Override
    public void update(Graphics2D g2d, double width, double height, int depth, int[] sliderVariables) {
        drawTriangle(g2d, 0, 0, Math.min(width, height), depth);
    }

    private static void drawTriangle(Graphics2D g2d, double x, double y, double side, int depth) {
        if (depth == 0) {
            Path2D.Double triangle = new Path2D.Double();
            triangle.moveTo(x + side / 2, y);
            triangle.lineTo(x, y + side);
            triangle.lineTo(x + side, y + side);
            g2d.fill(triangle);
            return;
        }

        drawTriangle(g2d, x + side / 4, y, side / 2, depth - 1);
        drawTriangle(g2d, x, y + side / 2, side / 2, depth - 1);
        drawTriangle(g2d, x + side / 2, y + side / 2, side / 2, depth - 1);
    }
}
