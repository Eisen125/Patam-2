package main.model.anomalyDetection;

public class AnomalyReport {
	public final String description;
	public final  long timeStep;

	public AnomalyReport(String description, long timeStep){
		this.description=description;
		this.timeStep=timeStep;
	}
}
