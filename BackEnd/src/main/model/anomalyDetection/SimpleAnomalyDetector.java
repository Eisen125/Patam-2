package main.model.anomalyDetection;

import main.model.statistics.Line;
import main.model.statistics.Point;
import main.model.statistics.StatLib;

import java.util.ArrayList;
import java.util.List;

public class SimpleAnomalyDetector implements TimeSeriesAnomalyDetector {

	int rowNumber;
	static float minCorrelation = (float)0.9;
	List<CorrelatedFeatures> cf;
	List <AnomalyReport> ar;

	public List<CorrelatedFeatures> getCf() {
		return cf;
	}

	public void setCf(List<CorrelatedFeatures> cf) {
		this.cf = cf;
	}

	public static float getMinCorrelation() {
		return minCorrelation;
	}

	public static void setMinCorrelation(float minCorrelation) {
		SimpleAnomalyDetector.minCorrelation = minCorrelation;
	}

	public int getRowNumber(){
		return this.rowNumber;
	}

	@Override
	public void learnNormal(TimeSeries ts) {
		rowNumber = ts.values.size();
		this.cf = new ArrayList<>();
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
			if(max > minCorrelation){
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
				CorrelatedFeatures c = new CorrelatedFeatures(ts.name.get(i), ts.name.get(col),max,l,max_dev);
				cf.add(c);
			}
		}
	}


	@Override
	public List<AnomalyReport> detect(TimeSeries ts) {
		this.ar = new ArrayList<>();
		for(int i=0 ; i<cf.size() ; i++){
			float[] f1 = ts.getCol(cf.get(i).feature1);
			float[] f2 = ts.getCol(cf.get(i).feature2);
			for(int j=0 ; j < ts.values.size() ; j++){
				 Point p = new Point(f1[j] , f2[j]);
				 float dis = StatLib.dev(p , cf.get(i).lin_reg);
				 dis = Math.abs(dis);
				 if(dis > cf.get(i).threshold){
					 ar.add(new AnomalyReport(cf.get(i).feature1 + "-" + cf.get(i).feature2 , j+1));
				 }
			}
		}
		return ar;
	}

	
	public List<CorrelatedFeatures> getNormalModel(){
		return this.cf;
	}

}
