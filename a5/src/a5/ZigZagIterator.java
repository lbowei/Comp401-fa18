package a5;

import java.util.Iterator;
// produces a Pixel iterator that iterates over the pixels of a Picture in zigzag order
// Zigzag order on google image:
// https://www.google.com/search?q=zigzag+order&source=lnms&tbm=isch&sa=X&ved=0ahUKEwie9q_zkbzeAhXLxFkKHYzNChc4ChD8BQgOKAE&biw=1440&bih=798
// Note: it works on any size of picture

public class ZigZagIterator implements Iterator<Pixel> {
	private Picture _source;
	private int _iter_x;
	private int _iter_y;

	public ZigZagIterator(Picture source) {
	//Throw illegal value if source picture is null;
		if (source == null) {
			throw new IllegalArgumentException("Picture is null");
		}
			_source = source;
			_iter_x = 0;
			_iter_y = 0;
	}
	
	//Iterator hasNext(), return false if current iter_x/y out of range.
	@Override
	public boolean hasNext() {
		if (_iter_x >= _source.getWidth()  || _iter_y >= _source.getHeight()) {
			return false;
		}
			return true;
	}
	
	// Iterator next() method, divide the point on the source picture in two part,
	// one is _iter_x + _iter_y is even, the other is odd,
	// The two part has different activities, and each has three distinct activities.
	@Override
	public Pixel next() {
		Pixel next_pix = _source.getPixel(_iter_x, _iter_y);
		if (checkEven(_iter_x, _iter_y)) {
			if (_iter_x < _source.getWidth() - 1 && _iter_y >= 1) {
				_iter_x++; _iter_y--;
			} else if (_iter_x < _source.getWidth() - 1) {
				_iter_x++;
			} else {
				_iter_y++;
			}
		}else {
			if ( _iter_y < _source.getHeight() - 1&& _iter_x >= 1) {
				_iter_y++; _iter_x--;
			} else if (_iter_y < _source.getHeight() - 1) {
				_iter_y++;
			} else {
				_iter_x++;
			}
		}
		return next_pix;
	}
	// check if even - method.
	public boolean checkEven(int x, int y) {
		if ((x + y) % 2 == 0) {
			return true;
		} else return false;
	}
}
