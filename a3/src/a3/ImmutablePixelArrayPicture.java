package a3;

public class ImmutablePixelArrayPicture implements Picture {
	private int _width;
	private int _height;
	private Pixel[][] _pixel_array;
	private Pixel _initial_value;


	// Creates new object using values provided by pixel_array, matching in size.
	public ImmutablePixelArrayPicture(Pixel[][] pixel_array) {


		if (pixel_array == null) {
			throw new IllegalArgumentException();
		}
		if (pixel_array.length <= 0) {
			throw new IllegalArgumentException();
		}
		for (Pixel[] p : pixel_array) {
			if (p == null || p.length <= 0) {
				throw new IllegalArgumentException();
			}
		}
		for (int q = 0; q < pixel_array.length; q++) {
			if (pixel_array[q].length != pixel_array[0].length) {
				throw new IllegalArgumentException();
			}
		}
		for (int i = 0; i < pixel_array.length; i++) {
			for (int j = 0; j < pixel_array[0].length; j++) {
				if (pixel_array[i][j] == null) {
					throw new IllegalArgumentException();
				}
			}
		}

		_pixel_array = pixel_array;
		_width = pixel_array.length;
		_height = pixel_array[0].length;

	}

	// Creates new object by providing geometry and initial value for all pixels.
	public ImmutablePixelArrayPicture(int width, int height, Pixel initial_value) {
		//
		if (width <= 0 || height <= 0 || initial_value == null) {
			throw new IllegalArgumentException();
		}
		_pixel_array = new Pixel[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				_pixel_array[i][j] = initial_value;
			}
		}
		_width = width;
		_height = height;

	}

	// Creates new object by providing geometry. Initial value should be medium gray.
	public ImmutablePixelArrayPicture(int width, int height) {
		this(width, height, new ColorPixel(0.5, 0.5, 0.5));

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
		} else {
			return _pixel_array[x][y];
		}
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException();
		}
		if (x >= _width || y >= _height) {
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
		pixel[x][y] = p;
		return new MutablePixelArrayPicture(pixel);
		
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
		Pixel[][] pixel = new Pixel[_width][_height];
		for (int i = 0; i < _width; i++) {
			for (int j = 0; j < _height; j++) {
				pixel[i][j] = getPixel(i, j);
			}
		}
		pixel[x][y] = pixel[x][y].blend(p, factor);
		return new MutablePixelArrayPicture(pixel);

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







