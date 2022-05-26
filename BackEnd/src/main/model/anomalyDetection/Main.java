package main.model.anomalyDetection;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Integer[] arr={1,2,3,4,5};
        Integer[] n= Arrays.copyOfRange(arr, 2,5);
        //System.out.println(n.for);
        for (Integer integer : n) {
            System.out.println(integer);
        }
    }
}
