

public class Circle {
    Point center;
    float radius;

    public Circle(Point center , float radius){
        this.center = center;
        this.radius = radius;
    }

    //check whether a point lies inside or on the boundaries of the circle
    public boolean isInside(Point p){
        if(MathLib.euclideanDist(this.center,p) <= this.radius){
            return true;
        }
        return false;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
