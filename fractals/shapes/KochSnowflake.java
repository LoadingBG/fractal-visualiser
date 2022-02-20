package fractals.shapes;

import java.awt.Graphics2D;

public class KochSnowflake implements FractalShape {
    @Override
    public int maxDepth() {
        return 9;
    }

    @Override
    public int orientationDivisionCount() {
        return 3;
    }

    @Override
    public SliderSpec[] variableSpecs() {
        return new SliderSpec[] {
            new SliderSpec(0, 90, 60, 10, 5, "Angle")
        };
    }

    @Override
    public void update(Graphics2D g2d, double width, double height, int depth, int[] sliderVariables) {
        g2d.translate(0, height);
        drawLine(g2d, width, depth, Math.toRadians(sliderVariables[0]));
    }

    private static void drawLine(Graphics2D g2d, double len, int depth, double deltaAngle) {
        if (depth == 0) {
            g2d.drawLine(0, 0, (int) len, 0);
            return;
        }

        double newLen = len / 2 / (1 + Math.cos(deltaAngle));

        drawLine(g2d, newLen, depth - 1, deltaAngle);
        g2d.translate(newLen, 0);
        g2d.rotate(-deltaAngle);
        drawLine(g2d, newLen, depth - 1, deltaAngle);
        g2d.rotate(deltaAngle);
        g2d.translate(len - 2 * newLen, 0);
        g2d.rotate(deltaAngle);
        g2d.translate(-newLen, 0);
        drawLine(g2d, newLen, depth - 1, deltaAngle);
        g2d.translate(newLen, 0);
        g2d.rotate(-deltaAngle);
        drawLine(g2d, newLen, depth - 1, deltaAngle);
        g2d.translate(newLen - len, 0);
    }
}
