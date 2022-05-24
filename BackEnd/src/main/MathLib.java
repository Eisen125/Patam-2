package main;

import java.util.ArrayList;

public class MathLib {

    //function to return the euclidean distance between two pionts
    public static double euclideanDist(Point a,Point b){
        return Math.sqrt(Math.pow(a.x-b.x,2)+Math.pow(a.y-b.y,2));
    }

    //Helper method to get a circle defined by 3 points
    public static Point getCircleCenter(double bx,double by,double cx,double cy){
        double b = bx * bx + by * by;
        double c = cx * cx + cy * cy;
        double d = bx * cy - by * cx;
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
        Point p = new Point((a.x + b.x) / 2.0 , (a.y + b.y) / 2.0);

        //set the radius to be hallf the distance ab
        Circle c = new Circle(p , euclideanDist(a , b) / 2.0);
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

}
