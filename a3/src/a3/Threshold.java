package a3;

public class Threshold implements PixelTransformation{
	private double _threshold;
	
	
	public Threshold (double threshold) {
		if(threshold>1||threshold<0) {
			throw new IllegalArgumentException();
		}
		_threshold = threshold;
	}




	@Override
	public Pixel transform(Pixel p) {
		Pixel a = null;
		if(p==null) {
			throw new IllegalArgumentException();
		}
		
		if(p.getIntensity()>_threshold) {
			a=  new GrayPixel(1.0);
		}else {
			a= new GrayPixel(0.0);
		}
	
		return a;
	}
	
}
