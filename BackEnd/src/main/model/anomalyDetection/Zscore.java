package main.model.anomalyDetection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Zscore implements TimeSeriesAnomalyDetector {
   // Point[] points;
    float threshold;
    List<String> notCorrelatedCol;
    HashMap<String,Float> thresholdMap;
    TimeSeries ts;

    @Override
    public void learnNormal(TimeSeries ts) {
        thresholdMap=new HashMap<>();
       /* notCorrelatedColThreshold= new HashMap<String, Float>();
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


        }*/

        //notCorrelatedCol.forEach(p-> findMaxThreshold(p);

        ts.values.forEach(fs->{
            float maxThreshold=findMaxThreshold(FloatTofloat(fs));
            String header= ts.name.get(ts.values.indexOf(fs));
            if(!thresholdMap.containsKey(header))
                thresholdMap.put(header, maxThreshold);
        });
    }

    @Override
    public List<AnomalyReport> detect(TimeSeries ts) {
        return null;
    }


    public void setThreshold(float threshold) {
        this.threshold = threshold;
    }

    public static float findMaxThreshold(float[] col){
        float maxThreshold=0, tmpMaxThreshold=0, avg=0, si=0 ;
        for (int i = 0; i < col.length ; i++) {
            float[] subCol= Arrays.copyOfRange(col, 0,i-1);
            avg= StatLib.avg(subCol);
            si= (float) Math.sqrt(StatLib.var(subCol));
            tmpMaxThreshold=Math.abs((col[i]-avg)/si);
            if(tmpMaxThreshold>maxThreshold){
                maxThreshold=tmpMaxThreshold;
                tmpMaxThreshold=0;
            }
        }
        return maxThreshold;
    }

    public float[] FloatTofloat(Float[] col){
        float[] newCol=new float[col.length];
        for (int i = 0; i < col.length; i++) {newCol[i]=col[i]; }
        return newCol;
    }
}
