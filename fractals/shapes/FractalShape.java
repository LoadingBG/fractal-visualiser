package fractals.shapes;

import java.awt.Graphics2D;

public interface FractalShape {
    int maxDepth();

    int orientationDivisionCount();

    SliderSpec[] variableSpecs();

    void update(Graphics2D g2d, double width, double height, int depth, int[] sliderVariables);
}
