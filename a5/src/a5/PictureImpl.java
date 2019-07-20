package a5;

import java.util.Iterator;
// PictureImpl is inherited by SubPictureImpl & PixelArrayPicture,
// PixelArrayPicture is inherited by MutalblePixelArrayPicture and ImmutablePixelArrayPicture.

abstract public class PictureImpl implements Picture {
    private String _caption;
    public PictureImpl (String caption){
        if(caption == null){
            throw new IllegalArgumentException("captions is null");
        }else{
            _caption = caption;
        }
    }
    // getWidth(), getHeight(), getPixel(),some paint methods..
    // These method will be provided in subclass in detail.
    @Override
    abstract  public int getWidth();
    @Override
    abstract public int getHeight();
    @Override
    abstract public Pixel getPixel(int x, int y);

    // paint method, paint (x,y) with pixel p. factor means how much the original
    // color replaced by p
    @Override
    public Picture paint(int x, int y, Pixel p) {
        return paint(x, y, p, 1.0);
    }
    @Override
    abstract public Picture paint(int x, int y, Pixel p, double factor);
    @Override

    // paint method, paint (ax, ay) to (bx, by) these rectangle area
    // with pixel p.factor means how much the original color replaced by p
    public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
        return paint(ax, ay, bx, by, p, 1.0);
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

        Picture paint_pic = this;
        for(int x = min_x; x <= max_x; x++) {
            for (int y = min_y; y <= max_y; y++) {
                paint_pic = paint_pic.paint(x,y,p,factor);
            }
        }
        return paint_pic;
    }
    // paint method, paint (cx, cy) with radius r the circle area with p.
    @Override
    public Picture paint(int cx, int cy, double radius, Pixel p) {
        return paint(cx, cy, radius, p, 1.0);
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

        Picture paint_pic = this;
        for (int x=min_x; x <= max_x; x++) {
            for (int y=min_y; y<= max_y; y++) {
                if (Math.sqrt((cx-x)*(cx-x)+(cy-y)*(cy-y)) <= radius) {
                    paint_pic = paint_pic.paint(x,y,p,factor);
                }
            }
        }
        return paint_pic;
    }

    // paint method.
    @Override
    public Picture paint(int x, int y, Picture p) {
        return paint(x,y,p,1.0);
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

        Picture paint_pic = this;
        for (int px=0; px < p.getWidth() && px + x < getWidth(); px++) {
            for (int py=0; py < p.getHeight() && py + y < getHeight(); py++) {
                paint_pic = paint_pic.paint(px+x, py+y, p.getPixel(px, py), factor);
            }
        }
        return paint_pic;
    }

    // get the caption.
    @Override
    public String getCaption() {
        return _caption;
    }
    @Override
    public void setCaption(String caption) {
        if(caption == null){
            throw new IllegalArgumentException("caption is null");
        }else{
            _caption = caption;
        }
    }
    // extract a subpicture with (x, y) and width, height from the
    // source picture.
    @Override
    public SubPicture extract(int x, int y, int width, int height) {
        return new SubPictureImpl(this, x, y, width, height);
    }
    // four different Iterators: SampleIterator, WindowIterator,
    // TileIterator, zigzagIterator.
    @Override
    public Iterator<Pixel> sample(int init_x, int init_y, int dx, int dy) {
        return new SampleIterator(this, init_x, init_y, dx, dy);
    }
    @Override
    public Iterator<SubPicture> window(int window_width, int window_height) {
        return new WindowIterator(this, window_width, window_height);
    }
    @Override
    public Iterator<SubPicture> tile(int tile_width, int tile_height) {
        return new TileIterator(this, tile_width, tile_height);
    }
    @Override
    public Iterator<Pixel> zigzag() {
        return new ZigZagIterator(this);
    }
}
