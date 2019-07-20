//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a4;

public class ColorPixel implements Pixel {
    double _red;
    double _green;
    double _blue;

    public ColorPixel(double r, double g, double b) {
        if (r >= 0.0D && r <= 1.0D && g >= 0.0D && g <= 1.0D && b >= 0.0D && b <= 1.0D) {
            this._red = r;
            this._green = g;
            this._blue = b;
        } else {
            throw new IllegalArgumentException("Color component out of range");
        }
    }

    public double getRed() {
        return this._red;
    }

    public double getGreen() {
        return this._green;
    }

    public double getBlue() {
        return this._blue;
    }
}
