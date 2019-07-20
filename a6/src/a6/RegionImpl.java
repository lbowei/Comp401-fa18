package a6;

public class RegionImpl implements Region {
    private int _left;
    private int _top;
    private int _right;
    private int _bottom;
    public RegionImpl(int left, int top, int right, int bottom){
        if(left > right || top > bottom){
            throw new IllegalArgumentException("region out of bound");
        }
        _left = left;
        _top = top;
        _right = right;
        _bottom = bottom;
    }

    @Override
    public int getTop() {
        return _top;
    }

    @Override
    public int getBottom() {
        return _bottom;
    }

    @Override
    public int getLeft() {
        return _left;
    }

    @Override
    public int getRight() {
        return _right;
    }

    @Override
    public Region intersect(Region other) throws NoIntersectionException {
        if(other == null) {
            throw new NoIntersectionException();
        }
        int rleft   = Math.max(this.getLeft(), other.getLeft());
        int rtop    = Math.max(this.getTop(), other.getTop());
        int rright  = Math.min(this.getRight(), other.getRight());
        int rbottom = Math.min(this.getBottom(), other.getBottom());
        if(rtop > rbottom || rleft > rright){
            throw new NoIntersectionException();
        }
        return new RegionImpl(rleft,rtop,rright,rbottom);


    }

    @Override
    public Region union(Region other) {
        if (other == null) {
            return this;
        } else {
            int rleft = Math.min(this.getLeft(), other.getLeft());
            int rtop = Math.min(this.getTop(), other.getTop());
            int rright = Math.max(this.getRight(), other.getRight());
            int rbottom = Math.max(this.getBottom(), other.getBottom());

            return new RegionImpl(rleft, rtop, rright, rbottom);
        }
    }
}












