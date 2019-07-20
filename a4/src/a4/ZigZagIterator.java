//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a4;

import java.util.Iterator;

public class ZigZagIterator implements Iterator<Pixel> {
    private Picture _source;
    private int _cur_x;
    private int _cur_y;

    public ZigZagIterator(Picture picture) {
        if (picture == null) {
            throw new IllegalArgumentException("picture is null");
        } else {
            this._source = picture;
            this._cur_x = 0;
            this._cur_y = 0;
        }
    }

    public boolean hasNext() {
        return this._cur_x < this._source.getWidth() && this._cur_y < this._source.getHeight();
    }

    public Pixel next() {
        Pixel p = this._source.getPixel(this._cur_x, this._cur_y);
        if ((this._cur_x + this._cur_y) % 2 == 0) {
            if (this._cur_x == this._source.getWidth() - 1) {
                ++this._cur_y;
            } else if (this._cur_y == 0) {
                ++this._cur_x;
            } else {
                --this._cur_y;
                ++this._cur_x;
            }
        } else if (this._cur_y == this._source.getHeight() - 1) {
            ++this._cur_x;
        } else if (this._cur_x == 0) {
            ++this._cur_y;
        } else {
            --this._cur_x;
            ++this._cur_y;
        }

        return p;
    }
}
