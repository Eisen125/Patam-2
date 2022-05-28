package main.model.anomalyDetection;

import java.util.HashMap;
import java.util.List;

public interface TimeSeriesAnomalyDetector {
	void learnNormal(TimeSeries ts);
	List<AnomalyReport> detect(TimeSeries ts);
}