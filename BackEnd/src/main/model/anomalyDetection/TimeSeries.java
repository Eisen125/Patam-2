package main.model.anomalyDetection;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TimeSeries {
	List<String> name;
	List<Float[]> values;
	int timeStep;

	public TimeSeries(String csvFileName) {
		name = new ArrayList<>();
		this.values = new ArrayList<>();
		try{
			BufferedReader br = new BufferedReader(new FileReader(csvFileName));
			String line = "";
			String[] s = null;
			line = br.readLine();
			name.addAll(Arrays.asList(line.split(",")));
			while((line = br.readLine()) != null){
				s = line.split(",");
				values.add(toFloat(s));
			}
			timeStep=values.size();
		}
		catch(IOException e){
			e.printStackTrace();
		}

	}


	public static Float[] toFloat(String [] s){
		Float[] f = new Float[s.length];
		for(int i = 0 ;i<s.length;i++){
			f[i] = Float.parseFloat(s[i]);
		}
		return f;
	}

	/*
	public static String[] toString(float[] f){
		String[] s = new String[f.length];
		for(int i = 0 ;i<f.length;i++){
			s[i] = Float.toString(f[i]);
		}
		return s;
	}*/

	public Float[] getLine(int timeStamp){
		return (values.get(timeStamp));
	}

	public float[] getCol(String feature){
		float[] f = new float[values.size()];
		for(int i = 0 ; i<values.size() ; i++){
			f[i] = values.get(i)[name.indexOf(feature)];
		}
		return f;
	}

	public int getTimeStep() {
		return timeStep;
	}
}
