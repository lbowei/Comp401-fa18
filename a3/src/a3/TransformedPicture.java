package a3;

public class TransformedPicture implements Picture {
	private Picture _source;

	private PixelTransformation _xform;

	private int _width;
	private int _height;

	private Pixel [][] pixel;
	private Pixel[][]  tpixel;
	private Pixel[][] source_pixel;
	private Picture _picture;

	public TransformedPicture (Picture source, PixelTransformation xform) {


		if(source == null) throw new IllegalArgumentException();

		if(xform == null) throw new IllegalArgumentException();

		_xform = xform;
		_width = source.getWidth();
		_height = source.getHeight();



		pixel = new Pixel[_width][_height];
		tpixel = new Pixel[_width][_height];
		source_pixel = new Pixel[_width][_height];

		for(int i=0;i<_width;i++){
			for(int j=0;j<_height;j++){
				//Pixel source_pixel = source.getPixel(i,j);
				source_pixel[i][j] = source.getPixel(i,j);

			}
		}
		for(int x=0;x<_width;x++){
			for(int y=0;y<_height;y++){
				pixel[x][y] = source_pixel[x][y];

				tpixel[x][y] = new ColorPixel(source_pixel[x][y].getRed(),source_pixel[x][y].getGreen(),source_pixel[x][y].getBlue());
			}
		}



		_picture = new MutablePixelArrayPicture(tpixel);

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
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException();
		}

		if (x >= _width || y >= _height) {
			throw new IllegalArgumentException();
		}else {
			return _xform.transform(tpixel[x][y]);
		}
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		_picture = new MutablePixelArrayPicture(tpixel);
		_picture =  _picture.paint(x,y,p);

		return _picture;
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException();
		}
		if (x >= _width || y >= _height) {
			throw new IllegalArgumentException();
		}
		if (p == null) {
			throw new IllegalArgumentException();
		}

		_picture = new MutablePixelArrayPicture(tpixel);

		_picture =  _picture.paint(x,y,p,factor);

		return _picture;


	}


	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		if (p == null) {
			throw new IllegalArgumentException();
		}
		if (ax < 0) {
			ax = 0;
		}
		if (ax > _width - 1) {
			ax = _width - 1;
		}
		if (ay < 0) {
			ay = 0;
		}
		if (ay > _height - 1) {
			ay = _height - 1;
		}
		if (bx < 0) {
			bx = 0;
		}
		if (bx > _width - 1) {
			bx = _width - 1;
		}
		if (by < 0) {
			by = 0;
		}
		if (by > _height - 1) {
			by = _height - 1;
		}
		Pixel[][] pixel = new Pixel[_width][_height];
		for (int i = 0; i < _width; i++) {
			for (int j = 0; j < _height; j++) {
				pixel[i][j] = getPixel(i, j);
			}
		}
		int x;
		int y;

		if ((ax < bx) && (ay < by)) {
			for (x = ax; x < bx + 1; x++) {
				for (y = ay; y < by + 1; y++) {
					pixel[x][y] = p;
				}
			}
		} else if ((ax < bx) && (ay > by)) {
			for (x = ax; x < bx + 1; x++) {
				for (y = by; y < ay + 1; y++) {
					pixel[x][y] = p;
				}
			}
		} else if ((ax > bx) && (ay < by)) {
			for (x = bx; x < ax + 1; x++) {
				for (y = ay; y < by + 1; y++) {
					pixel[x][y] = p;
				}
			}
		} else if ((ax > bx) && (ay > by)) {
			for (x = bx; x < ax + 1; x++) {
				for (y = by; y < ay + 1; y++) {
					pixel[x][y] = p;
				}
			}
		}
		return new MutablePixelArrayPicture(pixel);

	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if (p == null) {
			throw new IllegalArgumentException();
		}
		if (ax < 0) {
			ax = 0;
		}
		if (ax > _width - 1) {
			ax = _width - 1;
		}
		if (ay < 0) {
			ay = 0;
		}
		if (ay > _height - 1) {
			ay = _height - 1;
		}
		if (bx < 0) {
			bx = 0;
		}
		if (bx > _width - 1) {
			bx = _width - 1;
		}
		if (by < 0) {
			by = 0;
		}
		if (by > _height - 1) {
			by = _height - 1;
		}
		Pixel[][] pixel = new Pixel[_width][_height];
		for (int i = 0; i < _width; i++) {
			for (int j = 0; j < _height; j++) {
				pixel[i][j] = getPixel(i, j);
			}
		}

		//Picture picture = new ImmutablePixelArrayPicture(_pixel_array.clone());
		int x;
		int y;


		if ((ax < bx) && (ay < by)) {
			for (x = ax; x < bx + 1; x++) {
				for (y = ay; y < by + 1; y++) {
					pixel[x][y] = pixel[x][y].blend(p, factor);
				}
			}
		} else if ((ax < bx) && (ay > by)) {
			for (x = ax; x < bx + 1; x++) {
				for (y = by; y < ay + 1; y++) {
					pixel[x][y] = pixel[x][y].blend(p, factor);
				}
			}
		} else if ((ax > bx) && (ay < by)) {
			for (x = bx; x < ax + 1; x++) {
				for (y = ay; y < by + 1; y++) {
					pixel[x][y] = pixel[x][y].blend(p, factor);
				}
			}
		} else if ((ax > bx) && (ay > by)) {
			for (x = bx; x < ax + 1; x++) {
				for (y = by; y < ay + 1; y++) {
					pixel[x][y] = pixel[x][y].blend(p, factor);
				}
			}
		}
		return new MutablePixelArrayPicture(pixel);

	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		if (cx < 0 || cy < 0 || cx > _width || cy > _height) {
			throw new IllegalArgumentException();
		}
		if (radius < 0) {
			throw new IllegalArgumentException();
		}
		if (p == null) {
			throw new IllegalArgumentException();

		}
		Pixel[][] pixel = new Pixel[_width][_height];
		for (int i = 0; i < _width; i++) {
			for (int j = 0; j < _height; j++) {
				pixel[i][j] = getPixel(i, j);
			}
		}//Picture picture = new ImmutablePixelArrayPicture(_pixel_array.clone());
		int x = _width;
		int y = _height;
		for (int i = 0; i <= _width; i++) {
			for (int j = 0; j <= _height; j++) {
				if (Math.sqrt((i - cx) * (i - cx) + (j - cy) * (j - cy)) <= radius) {
					//picture= picture.paint(x, y, p);
					pixel[x][y] = p;
				}
			}
		}
		return new MutablePixelArrayPicture(pixel);
	}


	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		if (cx < 0 || cy < 0 || cx > _width || cy > _height) {
			throw new IllegalArgumentException();
		}
		if (radius < 0) {
			throw new IllegalArgumentException();
		}
		if (p == null) {
			throw new IllegalArgumentException();

		}
		Pixel[][] pixel = new Pixel[_width][_height];
		for (int i = 0; i < _width; i++) {
			for (int j = 0; j < _height; j++) {
				pixel[i][j] = getPixel(i, j);
			}
		}
		int x = _width;
		int y = _height;
		for (int i = 0; i <= _width; i++) {
			for (int j = 0; j <= _height; j++) {
				if (Math.sqrt((i - cx) * (i - cx) + (j - cy) * (j - cy)) <= radius) {
					pixel[x][y] = pixel[x][y].blend(p, factor);
				}
			}
		}
		return new MutablePixelArrayPicture(pixel);




	}
}
