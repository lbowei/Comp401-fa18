package a5;

import java.util.Iterator;
/*	
 *  sample iterator, Iterator<Pixel> sample_iter = p.sample(2,3,3,4);
	will sample a 15 times 15 picture in following order:
	(2,3) (5,3) (8,3) (11,3) (14,3) (2,7) (5,7) (8,7) (11,7) (14,7)
 */
public class SampleIterator implements Iterator<Pixel> {
	private Picture _source;
	private int _init_x;
	private int _init_y;
	private int _dx;
	private int _dy;
	private int _iter_x;
	private int _iter_y;
	
	//throw illegal value, sources is null, x or y out of bound.
	public SampleIterator(Picture source, int init_x, int init_y, int dx, int dy) {
		if(source == null) {
			throw new IllegalArgumentException("picture is null");
		}
		if(init_x < 0 || init_x > source.getWidth() || init_y < 0 || init_y > source.getHeight()){
			throw new IllegalArgumentException("init_x, y out of bound");
		}
		if(dx < 0 || dy < 0 || dx >= source.getWidth() || dy >= source.getHeight()){
			throw new IllegalArgumentException("illegal dx, dy");
		}
			_source = source;
			_init_x = init_x;
			_init_y = init_y;
			_dx = dx;
			_dy = dy;
			_iter_x = init_x;
			_iter_y = init_y;
	}
	// Iterator hasNext(), check if there exist next element.
	@Override
	public boolean hasNext() {
		if (_iter_x >= _source.getWidth() || _iter_y >= _source.getHeight()){
			return false;
		}
			return true;
	}
	//Iterator next(), return next Pixel in "sample" way;
	//The iterator begins with the specified initial pixel location
	//and then samples left to right the specified dx columns and
	//then top to bottom by the specified dy rows.
	@Override
	public Pixel next() {
		if(hasNext() == false) {
			throw new RuntimeException("!hasNext()");
		}
		Pixel next_pix = _source.getPixel(_iter_x, _iter_y);
		if (_iter_x + _dx >= _source.getWidth()){
			_iter_x = _init_x;
			_iter_y = _iter_y + _dy;
		}else{
			_iter_x += _dx;
		}
		return next_pix;
		}
	}

