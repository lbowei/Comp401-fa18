package a3;
import java.util.Arrays;
import java.util.Iterator;

public class MutablePixelArrayPicture implements Picture {
	private int _width;
	private int _height;
	private Pixel _initial_value;
	private Pixel [][] _pixel_array;
	
	
	
	public MutablePixelArrayPicture(Pixel[][] pixel_array) {
		
		if(pixel_array == null) {
			throw new IllegalArgumentException();
		}
		if(pixel_array.length <= 0) {
			throw new IllegalArgumentException();
		}
		for(Pixel[] p : pixel_array){
			if(p == null || p.length <= 0 ){
				throw new IllegalArgumentException();
			}
		}
		
		for(int q=0;q<pixel_array.length;q++) {
			if (pixel_array[q].length != pixel_array[0].length) {
				throw new IllegalArgumentException();
			}
		}
		for(int i=0;i<pixel_array.length;i++) {
			for(int j=0; j<pixel_array[0].length;j++) {
				if(pixel_array[i][j]==null) {
					throw new IllegalArgumentException();
				}
			}
		}
		_pixel_array = pixel_array;
		_width = pixel_array.length;
		_height = pixel_array[0].length;
	}



		

	
	public MutablePixelArrayPicture(int width, int height, Pixel initial_value) {
		
		if(initial_value == null) {
			throw new IllegalArgumentException();
		}
		if(width <= 0) {
			throw new IllegalArgumentException();
		}
		if(height <= 0) {
			throw new IllegalArgumentException();
		}
		_width = width;
		_height = height;
		_pixel_array = new Pixel[width][height];
		for(int i=0;i<width;i++) {
			for(int j=0; j<height;j++) {
				_pixel_array[i][j] = initial_value;
				}
			}
		}

	public MutablePixelArrayPicture(int width, int height) {
		this(width, height, new ColorPixel(0.5,0.5,0.5));
	}

	@Override
	public int getWidth() {
		return _width;
	}

	@Override
	public int getHeight() {
		return _height;
	}

	@Override
	public Pixel getPixel(int x, int y) {
		if (x>=_width || y>=_height) {
			throw new IllegalArgumentException();
		}else if(x<0 || y<0) {
			throw new IllegalArgumentException();
		}else {
			return _pixel_array[x][y];
		}	
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		if (p == null){
			throw new IllegalArgumentException();
		} else if (x>=_width || y>=_height || x<0 || y<0) {
			throw new IllegalArgumentException();
		} else {
			_pixel_array[x][y] = p;
		}
		return this;
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		
		if (p == null){
			throw new IllegalArgumentException();
		} else if (x>=_width || y>=_height || x<0 || y<0) {
			throw new IllegalArgumentException();
		} else {
			_pixel_array[x][y] = _pixel_array[x][y].blend(p,factor);
		}
		return this;
		
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		if (p == null){
			throw new IllegalArgumentException();
		}

		if(ax < 0) {
			ax = 0;
		}
		if(ax > _width - 1){
			ax=_width-1;
		}
		if(ay < 0) {
			ay = 0;
		}
		if(ay > _height - 1){
			ay=_height-1;
		}
		if(bx < 0) {
			bx = 0;
		}
		if(bx > _width - 1){
			bx=_width-1;
		}
		if(by < 0) {
			by = 0;
		}
		if(by > _height - 1){
			by=_height-1;
		}
		
		int x; int y;
		
		if((ax<bx)&&(ay<by)){
		for(x=ax;x<bx+1;x++) {
			for(y=ay;y<by+1;y++) {
				_pixel_array[x][y] = p;
				}
			}
		}else if((ax<bx)&&(ay>by)) {
		 for(x=ax;x<bx+1;x++) {
			for(y=by;y<ay+1;y++) {
				_pixel_array[x][y] = p;
				}
			}
		}else if((ax>bx)&&(ay<by)) {
		 for(x=bx;x<ax+1;x++) {
			for(y=ay;y<by+1;y++) {
				_pixel_array[x][y] = p;
				}
			}
		}else if((ax>bx)&&(ay>by)) {
		 for(x=bx;x<ax+1;x++) {
			for(y=by;y<ay+1;y++) {
				_pixel_array[x][y] = p;
				}
			}
	}
		return this;
	}
		

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if (p == null){
			throw new IllegalArgumentException();
		}
		if(ax < 0) {
			ax = 0;
		}
		if(ax > _width - 1){
			ax=_width-1;
		}
		if(ay < 0) {
			ay = 0;
		}
		if(ay > _height - 1){
			ay=_height-1;
		}
		if(bx < 0) {
			bx = 0;
		}
		if(bx > _width - 1){
			bx=_width-1;
		}
		if(by < 0) {
			by = 0;
		}
		if(by > _height - 1){
			by=_height-1;
		}
		
		
		int x; int y;
		if((ax<bx)&&(ay<by)){
		for(x=ax;x<bx;x++) {
			for(y=ay;y<by;y++) {
				_pixel_array[x][y] = _pixel_array[x][y].blend(p,factor);
				}
			}
		}else if((ax<bx)&&(ay>by)) {
		 for(x=ax;x<bx;x++) {
			for(y=by;y<ay;y++) {
				_pixel_array[x][y] = _pixel_array[x][y].blend(p,factor);

				}
			}
		}else if((ax>bx)&&(ay<by)) {
		 for(x=bx;x<ax;x++) {
			for(y=ay;y<by;y++) {
				_pixel_array[x][y] = _pixel_array[x][y].blend(p,factor);

				}
			}
		}else if((ax>bx)&&(ay>by)) {
		 for(x=bx;x<ax;x++) {
			for(y=by;y<ay;y++) {
				_pixel_array[x][y] = _pixel_array[x][y].blend(p,factor);
				}
			}
	}
		return this;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {

		if(cx<0) cx=0;
		if(cx>_width-1) cx=_width-1;
		if(cy<0) cy=0;
		if(cy>_height-1) cy=_height-1;


		if(radius<0) {
			throw new IllegalArgumentException();
		}
		if(p==null) {
			throw new IllegalArgumentException();

		}
		
		int x = _width;
		int y = _height;
		for(int i=0;i<=_width;i++) {
			for(int j = 0;j<=_height;j++) {
		if (Math.sqrt((i-cx)*(i-cx)+(j-cy)*(j-cy)) <= radius) {
			_pixel_array[i][j] = p;
		}
			}
		}
		return this;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		if(cx<0) cx=0;
		if(cx>_width-1) cx=_width-1;
		if(cy<0) cy=0;
		if(cy>_height-1) cy=_height-1;

		if(radius<0) {
			throw new IllegalArgumentException();
		}
		if(p==null) {
			throw new IllegalArgumentException();

		}
		
		int x = _width;
		int y = _height;
		for(int i=0;i<=_width;i++) {
			for(int j = 0;j<=_height;j++) {
		if (Math.sqrt((i-cx)*(i-cx)+(j-cy)*(j-cy)) <= radius) {
			_pixel_array[x][y] = _pixel_array[x][y].blend(p,factor);

		}
			}
		}
		return this;
	}

}






















