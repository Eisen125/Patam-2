package main.model.anomalyDetection;

public class CorrelatedFeaturesLiner extends CorrelatedFeatures{
    public final Line lin_reg;
    public final float threshold;

    public CorrelatedFeaturesLiner(String feature1, String feature2, float corrlation,Line lin_reg , float threshold) {
        super(feature1,feature2,corrlation);
        this.lin_reg = lin_reg;
        this.threshold = threshold;
    }
}
