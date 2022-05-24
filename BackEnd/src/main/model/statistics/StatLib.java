package main.model.statistics;

public class StatLib {

	// simple average
	public static float avg(float[] x){
		float sum = 0;
		for(int i = 0; i < x.length; i++){
			sum += x[i];
		}
		return  sum / x.length;
	}

	// returns the variance of X and Y
	public static float var(float[] x){
		float avg = avg(x);
		float sum = 0;
		for (int i=0 ; i< x.length ; i++){
			sum += (x[i] * x[i]);
		}
		sum /= x.length;

		return sum - (avg*avg);
	}

	// returns the covariance of X and Y
	public static float cov(float[] x, float[] y){
		float avg_x = avg(x);
		float avg_y = avg(y);
		float sum = 0;
		for(int i=0 ; i<x.length ; i++){
			sum += (x[i] - avg_x) * (y[i] - avg_y);
		}

		return sum / x.length;
	}


	// returns the Pearson correlation coefficient of X and Y
	public static float pearson(float[] x, float[] y){
		float cov = cov(x,y);
		float var_x = var(x);
		float var_y = var(y);
		float _x = (float)Math.sqrt(var_x);
		float _y = (float)Math.sqrt(var_y);
		return (cov / (_x * _y));
	}

	// performs a linear regression and returns the line equation
	public static Line linear_reg(Point[] points){
		float [] x = new float[points.length];
		float [] y = new float[points.length];
		for(int i = 0; i<points.length ; i++){
			x[i] = points[i].x;
			y[i] = points[i].y;
		}
		float avg_x = avg(x);
		float avg_y = avg(y);
		float cov = cov(x,y);
		float var_x = var(x);
		float a = cov / var_x;
		float b = avg_y - a * avg_x;
		Line l = new Line(a,b);
		return l;
	}

	// returns the deviation between point p and the line equation of the points
	public static float dev(Point p,Point[] points){
		Line l = linear_reg(points);
		float expected = l.f(p.x);
		float value = expected - p.y;
		if(value < 0){
			value *= -1;
		}
		return value;
	}

	// returns the deviation between point p and the line
	public static float dev(Point p,Line l){
		float expected = l.f(p.x);
		float value = expected - p.y;
		if(value < 0){
			value *= -1;
		}
		return value;
	}
	
}
