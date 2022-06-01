package main.model.anomalyDetection;

import static main.model.anomalyDetection.MathLib.minCircleTrivial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;



public class WelzlAlgorithm {

    public static Circle welzlHelper(ArrayList<Point> p ,ArrayList<Point> r , int n){
        if(n==0 || r.size()==3){
            return minCircleTrivial(r);
        }
        Random random = new Random();
        int idX =  random.nextInt(n)  % n;
        Point point = p.get(idX);
        Collections.swap(p,idX,n-1);
        Circle d = welzlHelper(p,r,n-1);
        if(d.isInside(point)){
            return d;
        }
        r.add(point);
        return welzlHelper(p,r,n-1);
    }

    public static Circle welzl(ArrayList<Point> p){
        ArrayList<Point> pCopy = new ArrayList<Point>();
        pCopy.addAll(p);
        Collections.shuffle(pCopy);
        ArrayList<Point> array = new ArrayList<Point>();
        return welzlHelper(pCopy,array, pCopy.size());
    }

}

