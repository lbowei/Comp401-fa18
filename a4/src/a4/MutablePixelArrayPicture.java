//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a4;

public class MutablePixelArrayPicture extends PixelArrayPicture implements Picture {
    public MutablePixelArrayPicture(Pixel[][] parray, String caption) {
        super(parray, caption);
    }

    public Picture paint(int x, int y, Pixel p, double factor) {
        if (x >= 0 && x < this.getWidth() && y >= 0 && y < this.getHeight()) {
            if (p == null) {
                throw new IllegalArgumentException("Pixel p is null");
            } else if (factor >= 0.0D && factor <= 1.0D) {
                this._pixels[x][y] = this._pixels[x][y].blend(p, factor);
                return this;
            } else {
                throw new IllegalArgumentException("factor is out of bounds");
            }
        } else {
            throw new IllegalArgumentException("x or y out of bounds");
        }
    }
}
