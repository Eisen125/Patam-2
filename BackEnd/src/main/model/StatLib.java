package main.model;

public class StatLib {

	// simple average
	public static double avg(double[] x){
		double sum = 0;
		for(int i = 0; i < x.length; i++){
			sum += x[i];
		}
		return  sum / x.length;
	}

	// returns the variance of X and Y
	public static double var(double[] x){
		double avg = avg(x);
		double sum = 0;
		for (int i=0 ; i< x.length ; i++){
			sum += (x[i] * x[i]);
		}
		sum /= x.length;

		return sum - (avg*avg);
	}

	// returns the covariance of X and Y
	public static double cov(double[] x, double[] y){
		double avg_x = avg(x);
		double avg_y = avg(y);
		double sum = 0;
		for(int i=0 ; i<x.length ; i++){
			sum += (x[i] - avg_x) * (y[i] - avg_y);
		}

		return sum / x.length;
	}


	// returns the Pearson correlation coefficient of X and Y
	public static double pearson(double[] x, double[] y){
		double cov = cov(x,y);
		double var_x = var(x);
		double var_y = var(y);
		double _x = Math.sqrt(var_x);
		double _y = Math.sqrt(var_y);
		return (cov / (_x * _y));
	}

	// performs a linear regression and returns the line equation
	public static Line linear_reg(Point[] points){
		double [] x = new double[points.length];
		double [] y = new double[points.length];
		for(int i = 0; i<points.length ; i++){
			x[i] = points[i].x;
			y[i] = points[i].y;
		}
		double avg_x = avg(x);
		double avg_y = avg(y);
		double cov = cov(x,y);
		double var_x = var(x);
		double a = cov / var_x;
		double b = avg_y - a * avg_x;
		Line l = new Line(a,b);
		return l;
	}

	// returns the deviation between point p and the line equation of the points
	public static double dev(Point p,Point[] points){
		Line l = linear_reg(points);
		double expected = l.f(p.x);
		double value = expected - p.y;
		if(value < 0){
			value *= -1;
		}
		return value;
	}

	// returns the deviation between point p and the line
	public static double dev(Point p,Line l){
		double expected = l.f(p.x);
		double value = expected - p.y;
		if(value < 0){
			value *= -1;
		}
		return value;
	}
	
}
