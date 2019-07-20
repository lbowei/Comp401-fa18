//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a4;

public class GrayPixel implements Pixel {
    private double _intensity;

    public GrayPixel(double intensity) {
        if (intensity >= 0.0D && intensity <= 1.0D) {
            this._intensity = intensity;
        } else {
            throw new IllegalArgumentException("intensity out of range");
        }
    }

    public double getRed() {
        return this._intensity;
    }

    public double getGreen() {
        return this._intensity;
    }

    public double getBlue() {
        return this._intensity;
    }

    public double getIntensity() {
        return this._intensity;
    }

    public Pixel lighten(double factor) {
        return new GrayPixel((1.0D - this._intensity) * factor + this._intensity);
    }

    public Pixel darken(double factor) {
        return new GrayPixel(this._intensity * (1.0D - factor));
    }
}
