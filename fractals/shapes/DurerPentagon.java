package fractals.shapes;

import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class DurerPentagon implements FractalShape {
    private static final double PHI = (1 + Math.sqrt(5)) / 2;

    private static final double SIN_36 = Math.sin(Math.toRadians(36));
    private static final double COS_72 = Math.cos(Math.toRadians(72));
    private static final double SIN_72 = Math.sin(Math.toRadians(72));

    @Override
    public int maxDepth() {
        return 10;
    }

    @Override
    public int orientationDivisionCount() {
        return 5;
    }

    @Override
    public SliderSpec[] variableSpecs() {
        return new SliderSpec[0];
    }

    @Override
    public void update(Graphics2D g2d, double width, double height, int depth, int[] sliderVariables) {
        drawUpPentagon(g2d, 0, 0, Math.min(width, height), depth);
    }

    private static void drawUpPentagon(Graphics2D g2d, double x, double y, double size, int depth) {
        double sideLength = size / PHI;

        if (depth == 0) {
            double middleOffsetY = sideLength * SIN_36;
            double horizSideOffsetX = sideLength * COS_72;

            Path2D.Double pentagon = new Path2D.Double();
            pentagon.moveTo(x + size / 2, y);
            pentagon.lineTo(x, y + middleOffsetY);
            pentagon.lineTo(x + horizSideOffsetX, y + size);
            pentagon.lineTo(x + size - horizSideOffsetX, y + size);
            pentagon.lineTo(x + size, y + middleOffsetY);
            g2d.fill(pentagon);

            return;
        }

        double newSize = sideLength * PHI * PHI / (1 + 2 * PHI);
        drawUpPentagon(g2d,
            x + (size - newSize) / 2,
            y,
            newSize, depth - 1);
        drawUpPentagon(g2d, // ?
            x,
            y + size - 2 * newSize,
            newSize, depth - 1);
        drawUpPentagon(g2d,
            x + size / 2 - newSize,
            y + size - newSize,
            newSize, depth - 1);
        drawUpPentagon(g2d,
            x + size / 2,
            y + size - newSize,
            newSize, depth - 1);
        drawUpPentagon(g2d,
            x + size - newSize,
            y + size - 2 * newSize,
            newSize, depth - 1);
        drawDownPentagon(g2d, // ?
            x + (size - newSize) / 2,
            y + newSize,
            newSize, depth - 1);
    }

    private static void drawDownPentagon(Graphics2D g2d, double x, double y, double size, int depth) {
        double sideLength = size / PHI;

        if (depth == 0) {
            double middleOffsetY = sideLength * SIN_72;
            double horizSideOffsetX = sideLength * COS_72;

            Path2D.Double pentagon = new Path2D.Double();
            pentagon.moveTo(x + horizSideOffsetX, y);
            pentagon.lineTo(x, y + middleOffsetY);
            pentagon.lineTo(x + size / 2, y + size);
            pentagon.lineTo(x + size, y + middleOffsetY);
            pentagon.lineTo(x + size - horizSideOffsetX, y);
            g2d.fill(pentagon);

            return;
        }

        double newSize = sideLength * PHI * PHI / (1 + 2 * PHI);
        drawDownPentagon(g2d,
            x + size / 2 - newSize,
            y,
            newSize, depth - 1);
        drawDownPentagon(g2d,
            x,
            y + newSize,
            newSize, depth - 1);
        drawDownPentagon(g2d,
            x + (size - newSize) / 2,
            y + size - newSize,
            newSize, depth - 1);
        drawDownPentagon(g2d,
            x + size - newSize,
            y + newSize,
            newSize, depth - 1);
        drawDownPentagon(g2d,
            x + size / 2,
            y,
            newSize, depth - 1);
        drawUpPentagon(g2d,
            x + (size - newSize) / 2,
            y + size - 2 * newSize,
            newSize, depth - 1);
    }
}
