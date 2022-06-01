package main.model.anomalyDetection;

import java.util.*;
import java.util.stream.Collectors;

import static main.model.anomalyDetection.WelzlAlgorithm.welzl;
import static main.model.anomalyDetection.Zscore.findMaxThreshold;

public class HybridAlgorithm implements TimeSeriesAnomalyDetector{
   public HashMap<String , CorrelatedFeaturesLiner> mapLiner;
   public HashMap<String , CorrelatedFeatureCircle> mapCircle;
   public HashMap<String , Float> mapZ;

   public HybridAlgorithm(){
       this.mapCircle = new HashMap<>();
       this.mapLiner = new HashMap<>();
       this.mapZ = new HashMap<>();
   }
    @Override
    public void learnNormal(TimeSeries ts) {
       Line linerRegression;
       float maxDev , threshold = 0;
        for(int i=0;i<ts.name.size() ; i++){
            float maxP = 0;
            int col = -1;
            for(int j=i+1 ; j<ts.name.size() ;j++) {
                float pearson = Math.abs(StatLib.pearson(ts.getCol(ts.name.get(i)),ts.getCol(ts.name.get(j))));
                if(pearson > maxP){
                    maxP = pearson;
                    col = j;
                }

            }
            Point[] pointArray = StatLib.arrayOfPoints(ts.getCol(i) , ts.getCol(col));
            //if correlation greater then 0.95 -> go to liner regression
            if(maxP >= 0.95){
                linerRegression = StatLib.linear_reg(pointArray);
                for(Point point: pointArray){
                    maxDev = StatLib.dev(point , linerRegression);
                    if(maxDev > threshold){
                        threshold = Math.abs(maxDev);
                    }
                }
                mapLiner.put(ts.name.get(i) , new CorrelatedFeaturesLiner(ts.name.get(i) , ts.name.get(col) , maxP , linerRegression , threshold));
            }
            //if correlation is between 0.5 to 0.95 go to welzl algorithm (circle)
            else if(0.5<= maxP && maxP<0.95){
                mapCircle.put(ts.name.get(i) , new CorrelatedFeatureCircle(ts.name.get(i) , ts.name.get(col) , maxP , welzl(new ArrayList<>(Arrays.asList(pointArray)))));
            }
            //else go to Zscore algorithm
            else {
                if(!mapZ.containsKey(i)){
                    mapZ.put(ts.name.get(i) , findMaxThreshold(ts.getCol(i)));
                }
                if(!mapZ.containsKey(col)){
                    mapZ.put(ts.name.get(col) , findMaxThreshold(ts.getCol(col)));
                }
            }
        }
    }

    @Override
    public List<AnomalyReport> detect(TimeSeries ts) {
        List<AnomalyReport> anomalyReports = new ArrayList<>();
        Point point;
        for(String f : ts.name){
            if(mapCircle.containsKey(f)){
                float[] feature1Correlated = ts.getCol(f);
                float[] feature2Correlated = ts.getCol(mapCircle.get(f).feature2);
                for(int i = 0;i<feature1Correlated.length;i++){
                    point = new Point(feature1Correlated[i], feature2Correlated[i]);
                    if(!mapCircle.get(f).circle.isInside(point)){
                        anomalyReports.add(new AnomalyReport(mapCircle.get(f).feature1 + "-" + mapCircle.get(f).feature2 , i+1));
                    }
                }
            }
            else if(mapLiner.containsKey(f)){
                float[] feature1Correlated = ts.getCol(f);
                float[] feature2Correlated = ts.getCol(mapCircle.get(f).feature2);
                for(int i = 0;i<feature1Correlated.length;i++){
                    point = new Point(feature1Correlated[i], feature2Correlated[i]);
                    if(StatLib.dev(point,mapLiner.get(f).lin_reg) > mapLiner.get(f).threshold + 0.015f){
                        anomalyReports.add(new AnomalyReport(mapCircle.get(f).feature1 + "-" + mapCircle.get(f).feature2 , i+1));
                    }
                }
            }
            else{
                //ask neta
                float tempZscore;
                for(int i=0;i<ts.values.size();i++){
                    tempZscore = findMaxThreshold(ts.getCol(f));
                    if(tempZscore > mapZ.get(f)){
                        anomalyReports.add(new AnomalyReport(mapCircle.get(f).feature1 + "-" + mapCircle.get(f).feature2 , i+1));
                    }
                }
            }
        }
        return anomalyReports;
    }
}
