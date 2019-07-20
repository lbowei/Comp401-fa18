package a5;
// SubPictureImpl inherits from PictureImpl
public class SubPictureImpl extends PictureImpl implements SubPicture {
	private Picture _source;
	private int _xoffset;
	private int _yoffset;
	private int _width;
	private int _height;
	private String _caption;

	// Besides caption, SubPictureImpl also has the picture source,
	// the xoffset, yoffset and the width and height of the subpicture,
	// Throw illegal argument when x,y,width, height out of range.
    public SubPictureImpl(Picture source, int xoffset, int yoffset, int width, int height) {
	 super(source.getCaption());
	 if(xoffset >= 0 && xoffset < source.getWidth()
	    && yoffset >=0 && yoffset < source.getHeight()
	    && width >=1 && xoffset + width <= source.getWidth()
		&& height >= 1 && yoffset + height <= source.getHeight()) {
		 this._source = source;
		 this._xoffset = xoffset;
		 this._yoffset = yoffset;
		 this._width = width;
		 this._height = height;
		 this._caption = source.getCaption();
	 }else
		 throw new IllegalArgumentException("Illegal subpicture geometry");  
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
	public int getXOffset() {
		return _xoffset;
	}
	@Override
	public int getYOffset() {
		return _yoffset;
	}
	@Override
	public Picture getSource() {
		return _source;
	}

	@Override
	public Pixel getPixel(int x, int y) {
		if( x < 0 || x >= getWidth() || y < 0 || y >= getHeight()){
			throw new IllegalArgumentException("x/y out of bound");
		}
		return _source.getPixel(_xoffset+x, _yoffset+y);
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		if(x < 0 || x > getWidth() || y < 0 || y > getHeight()){
			throw new IllegalArgumentException("x/y out of bound");
		}
		if( p == null){
			throw new IllegalArgumentException("Pixel p is null");
		}
		if (factor < 0.0 || factor > 1.0){
			throw new IllegalArgumentException("factor is out of bound");
		}
		Picture source_picture = _source.paint(x + this._xoffset, y + this._yoffset, p, factor);
		if(source_picture == _source){
			return this;
		}else {
			return new SubPictureImpl(source_picture, this._xoffset, this._yoffset,this._width,this._height);
		}
	}
}
