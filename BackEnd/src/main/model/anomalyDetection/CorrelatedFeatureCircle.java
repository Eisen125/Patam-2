package main.model.anomalyDetection;

public class CorrelatedFeatureCircle extends CorrelatedFeatures{
    public final Circle circle;

    public CorrelatedFeatureCircle(String feature1, String feature2, float corrlation , Circle circle) {
        super(feature1,feature2,corrlation);
        this.circle = circle;
    }
}
