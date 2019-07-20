package a5;

// PixelArrayPicture inherits from PictureImpl.
//
 abstract public class PixelArrayPicture extends PictureImpl implements Picture{
    protected Pixel[][] _pixels;
    
// Besides caption, iterator constructor has piexel array.
// Throw IllegalArguemnt if caption is null.
    protected PixelArrayPicture(Pixel[][] parray, String caption){
        super(caption);
        if(caption == null){
            throw new IllegalArgumentException("caption is null");
        }
        _pixels = copyPixelArray(parray);
    }
    
// getWidth(), getHeight(), getPixel() method with PixelArray.
     @Override
     public int getWidth() {
         return _pixels.length;
     }
     @Override
     public int getHeight() {
         return _pixels[0].length;
     }
     
     @Override
     public Pixel getPixel(int x, int y) {
         if (x < 0 || x >= this.getWidth() || y < 0 || y >= this.getHeight()) {
             throw new IllegalArgumentException("x or y out of bounds");
         }else {
             return _pixels[x][y];
         }
     }
     
// CopyPixelArray method. Provided by KMP :)
     private static Pixel[][] copyPixelArray(Pixel[][] pixel_array) {
        if (pixel_array == null) {
            throw new IllegalArgumentException("pixel_array is null");
        }
        int width = pixel_array.length;

        if (width == 0) {
            throw new IllegalArgumentException("pixel_array has illegal geometry");
        }

        for (int x=0; x<width; x++) {
            if (pixel_array[x] == null) {
                throw new IllegalArgumentException("pixel_array includes null columns");
            }
        }

        int height = pixel_array[0].length;
        if (height == 0) {
            throw new IllegalArgumentException("pixel_array has illegal geometry");
        }

        for (int x=0; x<width; x++) {
            if (pixel_array[x].length != height) {
                throw new IllegalArgumentException("Columns in picture are not all the same height.");
            }
        }

        for (int x=0; x<width; x++) {
            for (int y=0; y<height; y++) {
                if (pixel_array[x][y] == null) {
                    throw new IllegalArgumentException("pixel_array includes null pixels");
                }
            }
        }

        Pixel[][] copy = new Pixel[width][height];
        for (int x=0; x<width; x++) {
            for (int y=0; y<height; y++) {
                copy[x][y] = pixel_array[x][y];
            }
        }

        return copy;
    }
}