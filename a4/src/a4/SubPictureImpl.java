//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a4;

public class SubPictureImpl extends PictureImpl implements SubPicture {
    private Picture _src;
    private int _xoff;
    private int _yoff;
    private int _width;
    private int _height;
    private String _caption;

    public SubPictureImpl(Picture src, int xoffset, int yoffset, int width, int height) {
        super(checkSource(src).getCaption());
        if (xoffset >= 0 && xoffset < src.getWidth() && yoffset >= 0 && yoffset < src.getHeight() && width >= 1 && xoffset + width <= src.getWidth() && height >= 1 && yoffset + height <= src.getHeight()) {
            this._src = src;
            this._xoff = xoffset;
            this._yoff = yoffset;
            this._width = width;
            this._height = height;
            this._caption = src.getCaption();
        } else {
            throw new IllegalArgumentException("Illegal subpicture geometry");
        }
    }

    private static Picture checkSource(Picture src) {
        if (src == null) {
            throw new IllegalArgumentException("source picture is null");
        } else {
            return src;
        }
    }

    public int getWidth() {
        return this._width;
    }

    public int getHeight() {
        return this._height;
    }

    public Pixel getPixel(int x, int y) {
        if (x >= 0 && x < this.getWidth() && y >= 0 && y < this.getHeight()) {
            return this._src.getPixel(this._xoff + x, this._yoff + y);
        } else {
            throw new IllegalArgumentException("x or y out of bounds");
        }
    }

    public Picture paint(int x, int y, Pixel p, double factor) {
        if (x >= 0 && x < this.getWidth() && y >= 0 && y < this.getHeight()) {
            if (p == null) {
                throw new IllegalArgumentException("Pixel p is null");
            } else if (factor >= 0.0D && factor <= 1.0D) {
                Picture result = this._src.paint(this._xoff + x, this._yoff + y, p, factor);
                if (result == this._src) {
                    this._src = result;
                    return this;
                } else {
                    SubPicture new_sub = result.extract(this.getXOffset(), this.getYOffset(), this.getWidth(), this.getHeight());
                    new_sub.setCaption(this.getCaption());
                    return new_sub;
                }
            } else {
                throw new IllegalArgumentException("factor is out of bounds");
            }
        } else {
            throw new IllegalArgumentException("x or y out of bounds");
        }
    }

    public int getXOffset() {
        return this._xoff;
    }

    public int getYOffset() {
        return this._yoff;
    }

    public Picture getSource() {
        return this._src;
    }
}
