package main.model.statistics;



public class Circle {
    Point center;
    float radius;

    public Circle(Point Center , float radius){
        this.center = center;
        this.radius = radius;
    }

    //check whether a point lies inside or on the boundaries of the circle
    public boolean isInside(Point p){
        return MathLib.euclideanDist(this.center,p) <= this.radius;
    }
}
