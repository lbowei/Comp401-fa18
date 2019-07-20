//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CoordinateIterator implements Iterator<Coordinate> {
    private int _init_x;
    private int _init_y;
    private int _max_x;
    private int _max_y;
    private int _dx;
    private int _dy;
    private int _cur_x;
    private int _cur_y;

    public CoordinateIterator(int init_x, int init_y, int max_x, int max_y, int dx, int dy) {
        if (init_x >= 0 && init_x <= max_x && init_y >= 0 && init_y <= max_y) {
            if (dx >= 1 && dy >= 1) {
                this._init_x = init_x;
                this._init_y = init_y;
                this._max_x = max_x;
                this._max_y = max_y;
                this._dx = dx;
                this._dy = dy;
                this._cur_x = this._init_x;
                this._cur_y = this._init_y;
            } else {
                throw new IllegalArgumentException("dx or dy not positive");
            }
        } else {
            throw new IllegalArgumentException("illegal init_x or init_y");
        }
    }

    public boolean hasNext() {
        return this._cur_y <= this._max_y;
    }

    public Coordinate next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        } else {
            Coordinate next_coord = new Coordinate(this._cur_x, this._cur_y);
            this._cur_x += this._dx;
            if (this._cur_x > this._max_x) {
                this._cur_x = this._init_x;
                this._cur_y += this._dy;
            }

            return next_coord;
        }
    }
}
