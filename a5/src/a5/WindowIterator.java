package a5;

import java.util.Iterator;
/*
produce Subpicture iterators. slide from left to right, top to bottom using "window";
for example:Iterator<SubPicture> window_iter = p.window(3, 2), it will be like:
p.extract(0,0,3,2) p.extract(1,0,3,2) p.extract(2,0,3,2) p.extract(0,1,3,2)
p.extract(1,1,3,2) p.extract(2,1,3,2) p.extract(0,2,3,2) p.extract(1,2,3,2)
p.extract(2,2,3,2) p.extract(0,3,3,2) p.extract(1,3,3,2) p.extract(2,3,3,2)
 */
public class WindowIterator implements Iterator<SubPicture> {
	private Picture _source;
	private int _window_width;
	private int _window_height;
	private int _iter_x;
	private int _iter_y;
	private SubPicture _subpic;

	public WindowIterator(Picture source, int window_width, int window_height) {
	//throw illegalarugmentException when picture source is null,
	//window_width,height out of range.
		if(source == null) {
			throw new IllegalArgumentException("Picture is null");
		}
			_source = source;
		if(window_width <= 0 || window_width > _source.getWidth() || window_height <= 0 || window_height > _source.getHeight()) {
			throw new IllegalArgumentException("window size is illegal");
		}
			_window_width = window_width;
			_window_height = window_height;
			_iter_x = 0;
			_iter_y = 0;
			_subpic = source.extract(0,0,window_width,window_height);
	}
	
	//Iterator hasNext(), return false when reach the edge of source picture.
	@Override
	public boolean hasNext() {
		if( _iter_y + _window_height > _source.getHeight()){
			return false;
		}
			return true;
	}
	
	// Iterator next(), first check if there exist next element.
	// return "next" subpicture that the iterator "slides"
	// go down to next row if reach the rignt side of picture;

	@Override
	public SubPicture next() {
		if(!hasNext()) {
			throw new IllegalArgumentException("!hasNext");
		}
		SubPicture next_subpic = _source.extract(_iter_x, _iter_y,_window_width, _window_height);
		if(_iter_x + _window_width < _source.getWidth()){
			_iter_x = _iter_x + 1;
		}else{
			_iter_x = 0;
			_iter_y = _iter_y + 1;
		}
		return next_subpic;
	}

}
