//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a4;

import java.util.Iterator;

public abstract class PictureImpl implements Picture {
    private String _caption;

    protected PictureImpl(String caption) {
        if (caption == null) {
            throw new IllegalArgumentException("caption should not be null");
        } else {
            this._caption = caption;
        }
    }

    public String getCaption() {
        return this._caption;
    }

    public void setCaption(String caption) {
        if (caption == null) {
            throw new IllegalArgumentException("caption is null");
        } else {
            this._caption = caption;
        }
    }

    public Picture paint(int x, int y, Pixel p) {
        return this.paint(x, y, p, 1.0D);
    }

    public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
        return this.paint(ax, ay, bx, by, p, 1.0D);
    }

    public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
        if (p == null) {
            throw new IllegalArgumentException("Pixel p is null");
        } else if (factor >= 0.0D && factor <= 1.0D) {
            int min_x = ax < bx ? ax : bx;
            int max_x = ax > bx ? ax : bx;
            int min_y = ay < by ? ay : by;
            int max_y = ay > by ? ay : by;
            min_x = min_x < 0 ? 0 : min_x;
            min_y = min_y < 0 ? 0 : min_y;
            max_x = max_x > this.getWidth() - 1 ? this.getWidth() - 1 : max_x;
            max_y = max_y > this.getHeight() - 1 ? this.getHeight() - 1 : max_y;
            Picture result = this;

            for(int x = min_x; x <= max_x; ++x) {
                for(int y = min_y; y <= max_y; ++y) {
                    result = ((Picture)result).paint(x, y, p, factor);
                }
            }

            return (Picture)result;
        } else {
            throw new IllegalArgumentException("factor out of range");
        }
    }

    public Picture paint(int cx, int cy, double radius, Pixel p) {
        return this.paint(cx, cy, radius, p, 1.0D);
    }

    public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
        if (p == null) {
            throw new IllegalArgumentException("Pixel p is null");
        } else if (factor >= 0.0D && factor <= 1.0D) {
            if (radius < 0.0D) {
                throw new IllegalArgumentException("radius is negative");
            } else {
                int min_x = (int)((double)cx - (radius + 1.0D));
                int max_x = (int)((double)cx + radius + 1.0D);
                int min_y = (int)((double)cy - (radius + 1.0D));
                int max_y = (int)((double)cy + radius + 1.0D);
                min_x = min_x < 0 ? 0 : min_x;
                min_y = min_y < 0 ? 0 : min_y;
                max_x = max_x > this.getWidth() - 1 ? this.getWidth() - 1 : max_x;
                max_y = max_y > this.getHeight() - 1 ? this.getHeight() - 1 : max_y;
                Picture result = this;

                for(int x = min_x; x <= max_x; ++x) {
                    for(int y = min_y; y <= max_y; ++y) {
                        if (Math.sqrt((double)((cx - x) * (cx - x) + (cy - y) * (cy - y))) <= radius) {
                            result = ((Picture)result).paint(x, y, p, factor);
                        }
                    }
                }

                return (Picture)result;
            }
        } else {
            throw new IllegalArgumentException("factor out of range");
        }
    }

    public Picture paint(int x, int y, Picture p) {
        return this.paint(x, y, p, 1.0D);
    }

    public Picture paint(int x, int y, Picture p, double factor) {
        if (x >= 0 && x < this.getWidth() && y >= 0 && y < this.getHeight()) {
            if (factor >= 0.0D && factor <= 1.0D) {
                if (p == null) {
                    throw new IllegalArgumentException("Picture p is null");
                } else {
                    Picture result = this;

                    for(int px = 0; px < p.getWidth() && px + x < this.getWidth(); ++px) {
                        for(int py = 0; py < p.getHeight() && py + y < this.getHeight(); ++py) {
                            result = ((Picture)result).paint(px + x, py + y, p.getPixel(px, py), factor);
                        }
                    }

                    return (Picture)result;
                }
            } else {
                throw new IllegalArgumentException("factor out of range");
            }
        } else {
            throw new IllegalArgumentException("x or y out of range");
        }
    }

    public SubPicture extract(int x, int y, int width, int height) {
        return new SubPictureImpl(this, x, y, width, height);
    }

    public Iterator<Pixel> sample(int init_x, int init_y, int dx, int dy) {
        return new SampleIterator(this, init_x, init_y, dx, dy);
    }

    public Iterator<SubPicture> window(int window_width, int window_height) {
        return new WindowIterator(this, window_width, window_height);
    }

    public Iterator<SubPicture> tile(int tile_width, int tile_height) {
        return new TileIterator(this, tile_width, tile_height);
    }

    public Iterator<Pixel> zigzag() {
        return new ZigZagIterator(this);
    }
}
