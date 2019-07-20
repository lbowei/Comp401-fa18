package a6;

public class ROIObserverCombinedImpl implements ROIObserverCombined {
    private ROIObserver _observer;
    private Region _region;

    public ROIObserverCombinedImpl(ROIObserver observer, Region region){
            _observer = observer;
            _region = region;
    }
    
    @Override
    public Region getRegion() {
        return _region;
    }

    @Override
    public ROIObserver getROIObserver() {
        return _observer;
    }

    @Override
    public void notify(ObservablePicture picture, Region changed_region) {
        _observer.notify(picture, changed_region);
    }
}
