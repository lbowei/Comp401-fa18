package a5;
// ImmutablePixelArrayPicture inheris from PixelArrayPicture.
public class ImmutablePixelArrayPicture extends PixelArrayPicture implements Picture {

	public ImmutablePixelArrayPicture(Pixel[][] parray, String caption) {
		super(parray, caption);
	}
	
// Below are three different paint method for Immutable picture.
// Different from MutablePicture.
// throw illegal argument if p is null/factor or x,y  out of range.
// it should return a new MutalbePixelArrayPicture.
	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new IllegalArgumentException("x or y out of bounds");
		}
		if (p == null) {
			throw new IllegalArgumentException("Pixel p is null");
		}
		if (factor < 0.0 || factor > 1.0) {
			throw new IllegalArgumentException("factor is out of bounds");
		}
		
		return (new MutablePixelArrayPicture(_pixels, getCaption())).paint(x, y, p,factor);
	}
	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if (p == null) {
			throw new IllegalArgumentException("Pixel p is null");
		}

		if (factor < 0 || factor > 1.0) {
			throw new IllegalArgumentException("factor out of range");
		}

		int min_x = (ax < bx) ? ax : bx;
		int max_x = (ax > bx) ? ax : bx;
		int min_y = (ay < by) ? ay : by;
		int max_y = (ay > by) ? ay : by;

		min_x = (min_x < 0) ? 0 : min_x;
		min_y = (min_y < 0) ? 0 : min_y;
		max_x = (max_x > getWidth()-1) ? getWidth()-1 : max_x;
		max_y = (max_y > getHeight()-1) ? getHeight()-1 : max_y;

		Picture pic = this;
		for (int x=min_x; x <= max_x; x++) {
			for (int y=min_y; y<= max_y; y++) {
				pic = pic.paint(x,y,p,factor);
			}
		}
		return copyAsImmutable(pic);
	}
	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		if (p == null) {
			throw new IllegalArgumentException("Pixel p is null");
		}
		if (factor < 0 || factor > 1.0) {
			throw new IllegalArgumentException("factor out of range");
		}

		if (radius < 0) {
			throw new IllegalArgumentException("radius is negative");
		}

		int min_x = (int) (cx - (radius+1));
		int max_x = (int) (cx + (radius+1));
		int min_y = (int) (cy - (radius+1));
		int max_y = (int) (cy + (radius+1));

		min_x = (min_x < 0) ? 0 : min_x;
		min_y = (min_y < 0) ? 0 : min_y;
		max_x = (max_x > getWidth()-1) ? getWidth()-1 : max_x;
		max_y = (max_y > getHeight()-1) ? getHeight()-1 : max_y;

		Picture pic = this;
		for (int x=min_x; x <= max_x; x++) {
			for (int y=min_y; y<= max_y; y++) {
				if (Math.sqrt((cx-x)*(cx-x)+(cy-y)*(cy-y)) <= radius) {
					pic = pic.paint(x,y,p,factor);
				}
			}
		}
		return copyAsImmutable(pic);
	}

	@Override
	public Picture paint(int x, int y, Picture p, double factor) {
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new IllegalArgumentException("x or y out of range");
		}

		if (factor < 0 || factor > 1.0) {
			throw new IllegalArgumentException("factor out of range");
		}

		if (p == null) {
			throw new IllegalArgumentException("Picture p is null");
		}

		Picture result = this;
		for (int px=0; px < p.getWidth() && px + x < getWidth(); px++) {
			for (int py=0; py < p.getHeight() && py + y < getHeight(); py++) {
				result = result.paint(px+x, py+y, p.getPixel(px, py), factor);
			}
		}
		return copyAsImmutable(result);
	}

	private static Picture copyAsImmutable(Picture p) {
		if (p == null) {
			throw new IllegalArgumentException("Picture p is null");
		}

		Pixel[][] parray = new Pixel[p.getWidth()][p.getHeight()];
		for (int x=0; x<p.getWidth(); x++) {
			for (int y=0; y<p.getHeight(); y++) {
				parray[x][y] = p.getPixel(x, y);
			}
		}
		return new ImmutablePixelArrayPicture(parray, p.getCaption());
	}
}