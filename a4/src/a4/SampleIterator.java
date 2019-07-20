//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a4;

import java.util.Iterator;

public class SampleIterator implements Iterator<Pixel> {
    private CoordinateIterator _coord_iter;
    private Picture _source;

    public SampleIterator(Picture picture, int init_x, int init_y, int dx, int dy) {
        if (picture == null) {
            throw new IllegalArgumentException("picture is null");
        } else {
            this._source = picture;
            this._coord_iter = new CoordinateIterator(init_x, init_y, this._source.getWidth() - 1, this._source.getHeight() - 1, dx, dy);
        }
    }

    public boolean hasNext() {
        return this._coord_iter.hasNext();
    }

    public Pixel next() {
        Coordinate c = this._coord_iter.next();
        return this._source.getPixel(c.getX(), c.getY());
    }
}
