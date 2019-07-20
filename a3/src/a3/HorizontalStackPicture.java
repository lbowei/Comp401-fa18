package a3;

public class HorizontalStackPicture implements Picture {
	private int _width;
	private int _height;
	private Picture _left;
	private Picture _right;
	private Picture _top;
	private Picture _bottom;
	private Pixel [][] _pixel_array;
	private Pixel _initial_value;


	public HorizontalStackPicture(Picture left, Picture right) {
//		if(left == null || right == null || left.getHeight() != right.getHeight())
//			{ new IllegalArgumentException();}
//
		if(left==null || right == null)	throw new IllegalArgumentException();

			_width = left.getWidth() + right.getWidth();
		_height = left.getHeight() ;
		
		_pixel_array = new Pixel[_width][_height];
		
		for(int i=0; i<left.getWidth();i++) {
			for(int j=0; j<_height;j++) {
				_pixel_array[i][j] = left.getPixel(i, j);
			}
		}
			
			
		for (int x = left.getWidth(); x<_width;x++){
			for(int y=0; y<_height;y++) {
				_pixel_array[x][y] = right.getPixel(x-left.getWidth(),y );

			}
		}	
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
			//Pixel [][] pixel_array1= null;
			//_pixel_array[x][y] = _initial_value.blend(p, factor);
			_pixel_array[x][y] = _pixel_array[x][y].blend(p,factor);
		}
		return this;
		
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		if (p == null){
			throw new IllegalArgumentException();
		}
		if(ax<0 || ay<0 || bx<0 || by<0) {
			throw new IllegalArgumentException();
		}
		if( ax>_width || bx>_width || ay>_height || by>_height) {
			throw new IllegalArgumentException();
		}
		
		
		int x; int y;
		
		if((ax<bx)&&(ay<by)){
		for(x=ax;x<bx;x++) {
			for(y=ay;y<by;y++) {
				_pixel_array[x][y] = p;
				}
			}
		}else if((ax<bx)&&(ay>by)) {
		 for(x=ax;x<bx;x++) {
			for(y=by;y<ay;y++) {
				_pixel_array[x][y] = p;
				}
			}
		}else if((ax>bx)&&(ay<by)) {
		 for(x=bx;x<ax;x++) {
			for(y=ay;y<by;y++) {
				_pixel_array[x][y] = p;
				}
			}
		}else if((ax>bx)&&(ay>by)) {
		 for(x=bx;x<ax;x++) {
			for(y=by;y<ay;y++) {
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
		if(ax<0 || ay<0 || bx<0 || by<0) {
			throw new IllegalArgumentException();
		}
		if( ax>_width || bx>_width || ay>_height || by>_height) {
			throw new IllegalArgumentException();
		}
		
		
		int x; int y;
		if((ax<bx)&&(ay<by)){
		for(x=ax;x<bx;x++) {
			for(y=ay;y<by;y++) {
				//_pixel_array[x][y] = (ColorPixel)_initial_value.blend(p, factor);
				_pixel_array[x][y] = _pixel_array[x][y].blend(p,factor);
				}
			}
		}else if((ax<bx)&&(ay>by)) {
		 for(x=ax;x<bx;x++) {
			for(y=by;y<ay;y++) {
				//_pixel_array[x][y] = (ColorPixel)_initial_value.blend(p, factor);
				_pixel_array[x][y] = _pixel_array[x][y].blend(p,factor);

				}
			}
		}else if((ax>bx)&&(ay<by)) {
		 for(x=bx;x<ax;x++) {
			for(y=ay;y<by;y++) {
				//_pixel_array[x][y] = (ColorPixel)_initial_value.blend(p, factor);
				_pixel_array[x][y] = _pixel_array[x][y].blend(p,factor);

				}
			}
		}else if((ax>bx)&&(ay>by)) {
		 for(x=bx;x<ax;x++) {
			for(y=by;y<ay;y++) {
				//_pixel_array[x][y] = (ColorPixel)_initial_value.blend(p, factor);
				_pixel_array[x][y] = _pixel_array[x][y].blend(p,factor);

				}
			}
	}
		return this;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		if(cx<0 ||cy<0 ||cx> _width || cy>_height) {
			throw new IllegalArgumentException();
		}
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
		if(cx<0 ||cy<0 ||cx> _width || cy>_height) {
			throw new IllegalArgumentException();
		}
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
			//_pixel_array[x][y] = (ColorPixel)_initial_value.blend(p, factor);
			_pixel_array[x][y] = _pixel_array[x][y].blend(p,factor);

		}
			}
		}
		return this;
	}
}
 

