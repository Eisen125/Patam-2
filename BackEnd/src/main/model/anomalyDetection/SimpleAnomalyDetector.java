package main.model.anomalyDetection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SimpleAnomalyDetector implements TimeSeriesAnomalyDetector {

	int timeStep;
	float threshold;
	List<CorrelatedFeaturesLiner> correlatedFeatures;
	List <AnomalyReport> anomalyReports;

	public SimpleAnomalyDetector(){
		this.anomalyReports = new ArrayList<>();
		this.correlatedFeatures = new ArrayList<>();
		this.threshold = 0.9f;
	}

	public List<CorrelatedFeaturesLiner> getCorrelatedFeatures() {
		return correlatedFeatures;
	}

	public void setCorrelatedFeatures(List<CorrelatedFeaturesLiner> correlatedFeatures) {
		this.correlatedFeatures = correlatedFeatures;
	}

	public float getThreshold() {
		return threshold;
	}

	public void setThreshold(float threshold) {
		this.threshold = threshold;
	}

	public int getTimeStep(){
		return this.timeStep;
	}

	@Override
	public void learnNormal(TimeSeries ts) {
		timeStep = ts.values.size();
		this.correlatedFeatures = new ArrayList<>();
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
			if(max > threshold){
				Point[] p = new Point[ts.values.size()];
				for(int k=0;k<p.length;k++){
					p[k] = new Point(ts.getCol(ts.name.get(i))[k] , ts.getCol(ts.name.get(col))[k]);
					}
				Line l = StatLib.linear_reg(p);
				float max_dev = 0;
				for(int k=0;k<p.length;k++){
					if(StatLib.dev(p[k],l) > max_dev){
						max_dev = StatLib.dev(p[k],l);
					}
				}
				max_dev *= 1.1;
				CorrelatedFeaturesLiner c = new CorrelatedFeaturesLiner(ts.name.get(i), ts.name.get(col),max,l,max_dev);
				correlatedFeatures.add(c);
			}
		}
	}


	@Override
	public List<AnomalyReport> detect(TimeSeries ts) {
		this.anomalyReports = new ArrayList<>();
		for(int i = 0; i< correlatedFeatures.size() ; i++){
			float[] f1 = ts.getCol(correlatedFeatures.get(i).feature1);
			float[] f2 = ts.getCol(correlatedFeatures.get(i).feature2);
			for(int j=0 ; j < ts.values.size() ; j++){
				 Point p = new Point(f1[j] , f2[j]);
				 float dis = StatLib.dev(p , correlatedFeatures.get(i).lin_reg);
				 dis = Math.abs(dis);
				 if(dis > correlatedFeatures.get(i).threshold){
					 anomalyReports.add(new AnomalyReport(correlatedFeatures.get(i).feature1 + "-" + correlatedFeatures.get(i).feature2 , j+1));
				 }
			}
		}
		return anomalyReports;
	}

}
