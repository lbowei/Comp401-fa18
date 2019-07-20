package a3;

public class GammaCorrect implements PixelTransformation{
	private double _gamma;
	Pixel _pixel;

	public GammaCorrect(double gamma) {
		if (gamma <= 0 )
			throw new IllegalArgumentException();
		_gamma = gamma;


	}
	@Override
	public Pixel transform(Pixel p) {
		if(p==null) throw new IllegalArgumentException();


		double pRed = p.getRed();
		double pGreen = p.getGreen();
		double pBlue = p.getBlue();

		_pixel = new ColorPixel(Math.pow(pRed,(1.0/_gamma)), Math.pow(pGreen,(1.0/_gamma)), Math.pow(pBlue,(1.0/_gamma)));

		return _pixel;


	}

}
