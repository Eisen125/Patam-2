package main.model.anomalyDetection;

import java.util.ArrayList;

public class MathLib {

    //function to return the euclidean distance between two pionts
    public static float euclideanDist(Point a, Point b){
        return (float)Math.sqrt(Math.pow(a.x-b.x,2)+Math.pow(a.y-b.y,2));
    }

    //Helper method to get a circle defined by 3 points
    public static Point getCircleCenter(float bx,float by,float cx,float cy){
        float b = bx * bx + by * by;
        float c = cx * cx + cy * cy;
        float d = bx * cy - by * cx;
        Point p = new Point((cy * b - by * c) / (2 * d),(bx * c - cx * b)/(2 * d));
        return p;
    }

    //Function to return a unique circle that intersects 3 points.
    public static Circle circleFrom(Point a , Point b , Point c){
        Point i = getCircleCenter(b.x - a.x , b.y - a.y , c.x - a.x , c.y - a.y);
        i.x += a.x;
        i.y += a.y;
        Circle circle = new Circle(i ,euclideanDist(i , a));
        return circle;
    }

    //Function to return a unique circle that intersects 2 points.
    public static Circle circleFrom(Point a , Point b){
        //set the center to be the mid point of a and b
        Point p = new Point((a.x + b.x) / 2 , (a.y + b.y) / 2);

        //set the radius to be hallf the distance ab
        Circle c = new Circle(p , euclideanDist(a , b) / 2);
        return c;
    }

    //Function to check whether a circle encloses the given points
    public static boolean isValidCircle(Circle c , ArrayList<Point> pointsArray){
       for(Point p:pointsArray){
           if(!c.isInside(p)){
               return false;
           }
       }
        return true;
    }

    //Function to return the minimum enclosing circle for n<=3
    public static Circle minCircleTrivial(ArrayList<Point> pointArray){
        assert pointArray.size()<=3;
        if(pointArray.isEmpty()) {
            return new Circle(new Point(0,0), 0);
        }
        else if(pointArray.size() == 1){
            return new Circle(pointArray.get(0) , 0);
        }
        else if(pointArray.size() == 2){
            return circleFrom(pointArray.get(0) , pointArray.get(1));
        }

        for(int i=0;i<3;i++){
            for(int j=i+1;j<3;j++){
                Circle c = circleFrom(pointArray.get(i) , pointArray.get(j));
                if(isValidCircle(c,pointArray)){
                    return c;
                }
            }
        }
        return circleFrom(pointArray.get(0) , pointArray.get(1) , pointArray.get(2));
    }


}
