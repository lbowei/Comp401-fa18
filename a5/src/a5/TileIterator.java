package a5;

import java.util.Iterator;
// produce Subpicture iterators, as if you had cut the original picture
// into tiles that were tile_width wide and tile_height tall.
// for example, suppose we have 5times5 picture;
// Iterator<SubPicture> tile_iter = p.tile(2, 2) will give us:
// p.extract(0,0,2,2); p.extract(2,0,2,2);
// p.extract(0,2,2,2); p.extract(2,2,2,2);
public class TileIterator implements Iterator<SubPicture> {
	private Picture _source;
	private int _tile_width;
	private int _tile_height;
	private int _iter_x;
	private int _iter_y;

	public TileIterator(Picture source, int tile_width, int tile_height) {
		//throw IllegalArgumentException if picture source is null,
		//or tile width is out of range.
		if(source == null) {
			throw new IllegalArgumentException("Picture is null");
		}
			_source = source;
		if(tile_width <= 0 || tile_width > _source.getWidth() || tile_height <= 0 || tile_height > _source.getHeight()) {
			throw new IllegalArgumentException("window size is illegal");
		}else {
			_tile_width = tile_width;
			_tile_height = tile_height;
			_iter_x = 0;
			_iter_y = 0;
		}
	}
	// Iterator hasNext(), return false if current position plus the
	// tile width/height out of range of the picture.
	@Override
	public boolean hasNext() {
		if(_iter_x + _tile_width > _source.getWidth() || _iter_y + _tile_height > _source.getHeight()){
			return false;
		}
			return true;
	}

	// Iterator next(), first check if there exist next element.
	// return next subpicture which is different from windowIterator.
	// Partial tiles if the original picture width/height is not a perfect
	// multiple of the tile width/height are not produced.
	@Override
	public SubPicture next() {
		SubPicture next_subpic = _source.extract(_iter_x, _iter_y, _tile_width, _tile_height);
		if(_iter_x + 2*_tile_width <= _source.getWidth()){
			_iter_x = _iter_x + _tile_width;
		}else{
			_iter_x = 0;
			_iter_y = _iter_y + _tile_height;
		}
		return next_subpic;
	}

}
