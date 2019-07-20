//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a4;

public interface Pixel {
    double RED_INTENSITY_FACTOR = 0.2126D;
    double GREEN_INTENSITY_FACTOR = 0.7152D;
    double BLUE_INTENSITY_FACTOR = 0.0722D;
    Pixel WHITE = new GrayPixel(1.0D);
    Pixel BLACK = new GrayPixel(0.0D);
    double DEFAULT_EPSILON = 0.05D;

    double getRed();

    double getGreen();

    double getBlue();

    default double getIntensity() {
        return this.getRed() * 0.2126D + this.getGreen() * 0.7152D + this.getBlue() * 0.0722D;
    }

    default boolean equals(Pixel other) {
        return this.equals(other, 0.05D);
    }

    default boolean equals(Pixel other, double epsilon) {
        return Math.abs(this.getIntensity() - other.getIntensity()) < epsilon;
    }

    default Pixel lighten(double factor) {
        return this.blend(WHITE, factor);
    }

    default Pixel darken(double factor) {
        return this.blend(BLACK, factor);
    }

    default Pixel blend(Pixel other, double factor) {
        if (other == null) {
            throw new IllegalArgumentException("other is null");
        } else if (factor >= 0.0D && factor <= 1.0D) {
            double diff_red = other.getRed() - this.getRed();
            double diff_green = other.getGreen() - this.getGreen();
            double diff_blue = other.getBlue() - this.getBlue();
            return new ColorPixel(this.getRed() + diff_red * factor, this.getGreen() + diff_green * factor, this.getBlue() + diff_blue * factor);
        } else {
            throw new IllegalArgumentException("Illegal factor");
        }
    }
}
