//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a4;

import java.util.Iterator;

public class WindowIterator implements Iterator<SubPicture> {
    private Picture _source;
    private int _window_width;
    private int _window_height;
    private CoordinateIterator _coord_iter;

    public WindowIterator(Picture picture, int window_width, int window_height) {
        if (picture == null) {
            throw new IllegalArgumentException("picture is null");
        } else {
            this._source = picture;
            if (window_width >= 1 && window_width <= this._source.getWidth() && window_height >= 1 && window_height <= this._source.getHeight()) {
                this._window_width = window_width;
                this._window_height = window_height;
                this._coord_iter = new CoordinateIterator(0, 0, this._source.getWidth() - window_width, this._source.getHeight() - window_height, 1, 1);
            } else {
                throw new IllegalArgumentException("window size is illegal");
            }
        }
    }

    public boolean hasNext() {
        return this._coord_iter.hasNext();
    }

    public SubPicture next() {
        Coordinate c = this._coord_iter.next();
        return this._source.extract(c.getX(), c.getY(), this._window_width, this._window_height);
    }
}
