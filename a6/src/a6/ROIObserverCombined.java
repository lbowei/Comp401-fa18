package a6;

public interface ROIObserverCombined extends ROIObserver {
    Region getRegion();
    ROIObserver getROIObserver();
    void notify(ObservablePicture picture, Region changed_region);
}
