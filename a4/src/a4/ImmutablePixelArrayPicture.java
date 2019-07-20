//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a4;

public class ImmutablePixelArrayPicture extends PixelArrayPicture implements Picture {
    public ImmutablePixelArrayPicture(Pixel[][] parray, String caption) {
        super(parray, caption);
    }

    public Picture paint(int x, int y, Pixel p, double factor) {
        if (x >= 0 && x < this.getWidth() && y >= 0 && y < this.getHeight()) {
            if (p == null) {
                throw new IllegalArgumentException("Pixel p is null");
            } else if (factor >= 0.0D && factor <= 1.0D) {
                return (new MutablePixelArrayPicture(this._pixels, this.getCaption())).paint(x, y, p, factor);
            } else {
                throw new IllegalArgumentException("factor is out of bounds");
            }
        } else {
            throw new IllegalArgumentException("x or y out of bounds");
        }
    }

    public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
        return copyAsImmutable(super.paint(ax, ay, bx, by, p, factor));
    }

    public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
        return copyAsImmutable(super.paint(cx, cy, radius, p, factor));
    }

    public Picture paint(int x, int y, Picture p, double factor) {
        return copyAsImmutable(super.paint(x, y, p, factor));
    }

    private static Picture copyAsImmutable(Picture p) {
        if (p == null) {
            throw new IllegalArgumentException("Picture p is null");
        } else {
            Pixel[][] parray = new Pixel[p.getWidth()][p.getHeight()];

            for(int x = 0; x < p.getWidth(); ++x) {
                for(int y = 0; y < p.getHeight(); ++y) {
                    parray[x][y] = p.getPixel(x, y);
                }
            }

            return new ImmutablePixelArrayPicture(parray, p.getCaption());
        }
    }
}
