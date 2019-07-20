package a3;

public class MonochromePicture implements Picture {
	private int _width;
	private int _height;
	private Pixel _value;

	
	public MonochromePicture(int width, int height, Pixel value) {
		if(value == null) {
			throw new IllegalArgumentException();
		}
		if(width<= 0 || height <= 0) {
			throw new IllegalArgumentException();
		}
		
		_width = width;
		_height = height;
		_value = value;
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
	
		if(x<0 || y<0) {
			throw new IllegalArgumentException();
		}
		
		if (x>=_width || y>=_height) {
			throw new IllegalArgumentException();	
		}else {
			return _value;
		}
		
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		if(x<0 || y<0) {
			throw new IllegalArgumentException();
		}
		if (x>=_width || y>=_height) {
			throw new IllegalArgumentException();	
		}
		if(p == null) {
			throw new IllegalArgumentException();	
		}
		
		Pixel[][] pixel= new Pixel[_width][_height];
		for(int i = 0; i < _width; i ++){
			for(int j = 0; j < _height; j ++){
				pixel[i][j] = _value;
			}
		}
		pixel [x][y] = p;
		return new MutablePixelArrayPicture(pixel);
		
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		if(x<0 || y<0) {
			throw new IllegalArgumentException();
		}
		if (x>=_width || y>=_height) {
			throw new IllegalArgumentException();	
		}
		if(p == null) {
			throw new IllegalArgumentException();	
		}
		
		Pixel[][] pixel= new Pixel[_width][_height];
		pixel [x][y] = _value.blend(p, factor);
		return new MutablePixelArrayPicture(pixel);
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
		//Picture picture = new MutablePixelArrayPicture(_pixel_array.clone());
		Pixel[][] pixel= new Pixel[_width][_height];
		for(int i=0;i<_width;i++){
			for(int j=0;j<_height;j++){
				pixel[i][j]= _value;
			}
		}
		int x; int y;
		if((ax<=bx)&&(ay<=by)){
		for(x=ax;x< bx + 1;x++) {
			for(y=ay;y<by+1;y++) {
				pixel [x][y] = p;
				}
			}
		}else if((ax<=bx)&&(ay>by)) {
		 for(x=ax;x<bx+1;x++) {
			for(y=by;y<ay+1;y++) {
				pixel [x][y] = p;
				}
			}
		}else if((ax>bx)&&(ay<=by)) {
		 for(x=bx;x<ax+1;x++) {
			for(y=ay;y<by+1;y++) {
				pixel [x][y] = p;
				}
			}
		}else if((ax>bx)&&(ay>by)) {
		 for(x=bx;x<ax+1;x++) {
			for(y=by;y<ay+1;y++) {
				pixel [x][y] = p;
				}
			}
		}
		return new MutablePixelArrayPicture(pixel);

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
		
		
		//Picture picture = new MutablePixelArrayPicture(_pixel_array.clone());

		Pixel[][] pixel= new Pixel[_width][_height];	
		int x; int y;
		
		if((ax<bx)&&(ay<by)){
		for(x=ax;x<bx;x++) {
			for(y=ay;y<by;y++) {
				pixel[x][y] = _value.blend(p, factor);
				}
			}
		}else if((ax<bx)&&(ay>by)) {
		 for(x=ax;x<bx;x++) {
			for(y=by;y<ay;y++) {
				pixel[x][y] = _value.blend(p, factor);
				}
			}
		}else if((ax>bx)&&(ay<by)) {
		 for(x=bx;x<ax;x++) {
			for(y=ay;y<by;y++) {
				pixel[x][y] = _value.blend(p, factor);
				}
			}
		}else if((ax>bx)&&(ay>by)) {
		 for(x=bx;x<ax;x++) {
			for(y=by;y<ay;y++) {
				pixel[x][y] = _value.blend(p, factor);
				}
			}
		}
		return new MutablePixelArrayPicture(pixel);
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
		
	//	Picture picture = new MutablePixelArrayPicture(_pixel_array.clone());

		Pixel[][] pixel= new Pixel[_width][_height];	
		
		//ColorPixel[][] pixel= new ColorPixel[_width][_height];
		for(int x=0;x<_width;x++) {
			for(int y = 0;y<_height;y++) {


		if (Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy)) <= radius) {
			pixel [x][y] = p;
			}
			else{
				pixel [x][y] = _value;
		}
		}
	}
		return new MutablePixelArrayPicture(pixel);
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
		
		//Picture picture = new MutablePixelArrayPicture(_pixel_array.clone());

		Pixel[][] pixel= new Pixel[_width][_height];	
		
		//ColorPixel[][] pixel= new ColorPixel[_width][_height];
		for(int x=0;x<=2;x++) {
			for(int y = 0;y<=2;y++) {
		
		if (Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy)) <= radius) {
			pixel[x][y] = _value.blend(p, factor);
		}
			}
		}
		return new MutablePixelArrayPicture(pixel);
	}
	

}
