//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a4;

public abstract class PixelArrayPicture extends PictureImpl implements Picture {
    protected Pixel[][] _pixels;

    protected PixelArrayPicture(Pixel[][] parray, String caption) {
        super(caption);
        this._pixels = copyPixelArray(parray);
    }

    public int getWidth() {
        return this._pixels.length;
    }

    public int getHeight() {
        return this._pixels[0].length;
    }

    public Pixel getPixel(int x, int y) {
        if (x >= 0 && x < this.getWidth() && y >= 0 && y < this.getHeight()) {
            return this._pixels[x][y];
        } else {
            throw new IllegalArgumentException("x or y out of bounds");
        }
    }

    private static Pixel[][] copyPixelArray(Pixel[][] pixel_array) {
        if (pixel_array == null) {
            throw new IllegalArgumentException("pixel_array is null");
        } else {
            int width = pixel_array.length;
            if (width == 0) {
                throw new IllegalArgumentException("pixel_array has illegal geometry");
            } else {
                int height;
                for(height = 0; height < width; ++height) {
                    if (pixel_array[height] == null) {
                        throw new IllegalArgumentException("pixel_array includes null columns");
                    }
                }

                height = pixel_array[0].length;
                if (height == 0) {
                    throw new IllegalArgumentException("pixel_array has illegal geometry");
                } else {
                    int x;
                    for(x = 0; x < width; ++x) {
                        if (pixel_array[x].length != height) {
                            throw new IllegalArgumentException("Columns in picture are not all the same height.");
                        }
                    }

                    int x;
                    for(x = 0; x < width; ++x) {
                        for(x = 0; x < height; ++x) {
                            if (pixel_array[x][x] == null) {
                                throw new IllegalArgumentException("pixel_array includes null pixels");
                            }
                        }
                    }

                    Pixel[][] copy = new Pixel[width][height];

                    for(x = 0; x < width; ++x) {
                        for(int y = 0; y < height; ++y) {
                            copy[x][y] = pixel_array[x][y];
                        }
                    }

                    return copy;
                }
            }
        }
    }
}
