package main.model.anomalyDetection;

import main.model.statistics.StatLib;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Zscore implements TimeSeriesAnomalyDetector{
   // Point[] points;
    float threshold;
    List<String> notCorrelatedCol;
    HashMap<String, float> notCorrelatedColThreshold;
    TimeSeries ts;

    @Override
    public void learnNormal(TimeSeries ts) {
        notCorrelatedColThreshold= new HashMap<String, float>();
        this.notCorrelatedCol = new ArrayList<>();
        for(int i=0;i<ts.name.size() ; i++){
            float max = 0;
            int col = -1;
            for(int j=i+1 ; j<ts.name.size() ;j++) {
                float pearson = Math.abs(StatLib.pearson(ts.getCol(ts.name.get(i)),ts.getCol(ts.name.get(j))));
                if(pearson > max){
                    max = pearson;
                    col = j;
                }
            }
            if(max < threshold){
                notCorrelatedCol.add(ts.name.get(i));
            }
        }

        notCorrelatedCol.forEach(p-> findMaxThreshold(p, ts));
    }

    @Override
    public List<AnomalyReport> detect(TimeSeries ts) {
        return null;
    }

    public void setThreshold(float threshold) {
        this.threshold = threshold;
    }

    public void findMaxThreshold(String col, TimeSeries ts){

        for (int i = 0; i < ts.timeStep ; i++) {

        }
    }
}
