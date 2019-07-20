package a6;
import java.util.ArrayList;
import java.util.List;

public class ObservablePictureImpl implements ObservablePicture {
    private Picture _source_pic;
    private Region change_region;
    private boolean suspend_flag;
    private String _caption;
    private List<ROIObserverCombined> _observer;

    public ObservablePictureImpl(Picture p){
        if(p == null){
            throw new IllegalArgumentException("p can't be null");
        }else {
            _source_pic = p;
            //initial change_region is null "the overall changed region".
            change_region = null;
            suspend_flag = false;
            _observer = new ArrayList<ROIObserverCombined>();
        }
    }

    @Override
    public void registerROIObserver(ROIObserver observer, Region r) {
        if(r == null || observer == null){
            throw new IllegalArgumentException("Region/ROIObserver is null");
        }else{
            //add the observer with its region to list
            ROIObserverCombined add_tolist = new ROIObserverCombinedImpl(observer,r);
            _observer.add(add_tolist);
        }
    }

    @Override
    public void unregisterROIObservers(Region r) {
        if(r == null){
            throw new IllegalArgumentException();
        }else {
            //create a new empty list first;
            List<ROIObserverCombined> tmp = new ArrayList<ROIObserverCombined>();
            for(ROIObserverCombined obs: _observer){
                //if not intersect, then catch and add to tmp, finally
                //let observers = tmp, which is the observer that not
                //intersect with the region r.
                try{
                    obs.getRegion().intersect(r);
                }catch (NoIntersectionException e){
                    tmp.add(obs);
                }
            }
            _observer = tmp;
        }
    }

    @Override
    public void unregisterROIObserver(ROIObserver observer) {
        if(observer == null){
            throw new IllegalArgumentException();
        }
        //create new empty list.
        List<ROIObserverCombined> tmp = new ArrayList<ROIObserverCombined>();
        //go through the list, find if the observer that not equal to
        // observer, then add to the empty list, then let original list equal to the new list.
        for(ROIObserverCombined obs : _observer){
            if(obs.getROIObserver() == observer){
                //tmp = tmp;
            }else if(obs.getROIObserver() != observer){
                tmp.add(obs);
            }
        }
        _observer = tmp;
    }

    @Override
    public void suspendObservable() {
       suspend_flag = true;
    }
    @Override
    public void resumeObservable() {
        suspend_flag = false;
        noteObservers(change_region);
    }
    private void noteObservers(Region r){
        if(suspend_flag == true){
            return;
        }else{
            if(change_region == null){
                return;
            }else{
                for(ROIObserverCombined obs : _observer){
                    try{
                        // obs.getROIObserver().notify(this, obs.getRegion().intersect(r));
                        Region intersect_area = obs.getRegion().intersect(r);
                        obs.notify(this, intersect_area);
                    }catch (NoIntersectionException e){
                    }
                }change_region = null;
            }
        }
    }

    @Override
    public ROIObserver[] findROIObservers(Region r) {
        if(r == null){
            throw new IllegalArgumentException("r can't be null");
        }else {
            List<ROIObserver> result = new ArrayList<ROIObserver>();
            for (ROIObserverCombined obs : _observer) {
                try {
                    obs.getRegion().intersect(r); //if not intersect, throw exception
                    result.add(obs.getROIObserver());
                } catch (NoIntersectionException e) {
                   //
                }
            }
            ROIObserver[] array = new ROIObserver[result.size()];
            return result.toArray(array);
        }
    }

    @Override
    public int getWidth() {
        return _source_pic.getWidth();
    }
    @Override
    public int getHeight() {
        return _source_pic.getHeight();
    }
    @Override
    public Pixel getPixel(int x, int y) {
        return _source_pic.getPixel(x, y);
    }
    @Override
    public Picture paint(int x, int y, Pixel p) {
        return paint(x,y,p,1.0);
    }
    @Override
    public Picture paint(int x, int y, Pixel p, double factor) {
        _source_pic = _source_pic.paint(x, y, p, factor);
        Region region = new RegionImpl(x, y, x, y);
        change_region = region.union(change_region);
        noteObservers(change_region);
        return this;
    }
    @Override
    public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
        return paint(ax,ay,bx,by,p,1.0);
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

        _source_pic = _source_pic.paint(min_x,min_y,max_x,max_y,p,factor);
        Region region = new RegionImpl(min_x, min_y, max_x, max_y);
        change_region = region.union(change_region);
        noteObservers(change_region);
        return this;
    }
    @Override
    public Picture paint(int cx, int cy, double radius, Pixel p) {
        return paint(cx, cy, radius, p,1.0);
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

        for (int x=min_x; x <= max_x; x++) {
            for (int y=min_y; y<= max_y; y++) {
                if (Math.sqrt((cx-x)*(cx-x)+(cy-y)*(cy-y)) <= radius) {
                    _source_pic = _source_pic.paint(x,y,p,factor);
                }
            }
        }
        Region region = new RegionImpl((int)(cx-radius), (int)(cy-radius),(int)(cx+radius), (int)(cy+radius));
        change_region = region.union(change_region);
        noteObservers(change_region);
        return this;
    }

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

        for (int px=0; px < p.getWidth() && px + x < getWidth(); px++) {
            for (int py=0; py < p.getHeight() && py + y < getHeight(); py++) {
                _source_pic = _source_pic.paint(px+x, py+y, p.getPixel(px, py), factor);
            }
        }
        int right = Math.min(x+p.getWidth()-1,_source_pic.getWidth()-1);
        int bottom = Math.min(y+p.getHeight()-1,_source_pic.getHeight()-1);
        Region region = new RegionImpl(x,y,right, bottom);
        change_region = region.union(change_region);
        noteObservers(change_region);

        return this;
    }

    @Override
    public String getCaption() {
        return _source_pic.getCaption();
    }

    @Override
    public void setCaption(String caption) {
        if (caption == null) {
            throw new IllegalArgumentException("caption is null");
        }

        _caption = caption;
    }
    @Override
    public SubPicture extract(int x, int y, int width, int height) {
        return _source_pic.extract(x, y, width, height);
    }
}

