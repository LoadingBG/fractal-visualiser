package fractals.shapes;

public class SliderSpec {
    private int min;
    private int max;
    private int initial;
    private int majorTickSpacing;
    private int minorTickSpacing;
    private String label;

    public SliderSpec(int min, int max, int initial, int majorTickSpacing, int minorTickSpacing, String label) {
        this.min = min;
        this.max = max;
        this.initial = initial;
        this.majorTickSpacing = majorTickSpacing;
        this.minorTickSpacing = minorTickSpacing;
        this.label = label;
    }

    public int min() {
        return min;
    }

    public int max() {
        return max;
    }

    public int initial() {
        return initial;
    }

    public int majorTickSpacing() {
        return majorTickSpacing;
    }

    public int minorTickSpacing() {
        return majorTickSpacing;
    }

    public String label() {
        return label;
    }
}
