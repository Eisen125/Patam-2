package main.model.anomalyDetection;

public class HybridAlgorithm {






    public static Float[] toFloat(String [] s){
        Float[] f = new Float[s.length];
        for(int i = 0 ;i<s.length;i++){
            f[i] = Float.parseFloat(s[i]);
        }
        return f;
    }
}
