//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a4;

import java.util.Iterator;

public class TileIterator implements Iterator<SubPicture> {
    private Picture _source;
    private int _tile_width;
    private int _tile_height;
    private CoordinateIterator _coord_iter;

    public TileIterator(Picture picture, int tile_width, int tile_height) {
        if (picture == null) {
            throw new IllegalArgumentException("picture is null");
        } else {
            this._source = picture;
            if (tile_width >= 1 && tile_width <= this._source.getWidth() && tile_height >= 1 && tile_height <= this._source.getHeight()) {
                this._tile_width = tile_width;
                this._tile_height = tile_height;
                this._coord_iter = new CoordinateIterator(0, 0, this._source.getWidth() - tile_width, this._source.getHeight() - tile_height, this._tile_width, this._tile_height);
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
        return this._source.extract(c.getX(), c.getY(), this._tile_width, this._tile_height);
    }
}
