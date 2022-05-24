package Matala1;
import java.util.*;

public class Graph {
    HashMap<String, ArrayList<Edge>> graph;

    private class Edge {
        String v;
        int weight;

        public Edge(String v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }

    public void addEdge(String from, String to, int w, Boolean isBi) {
        Edge e = new Edge(from, w);
        if (!(graph.containsKey(from))) {
            graph.put(from, new ArrayList<>());
        }
        graph.get(from).add(e);
        if (isBi) {
            if (!(graph.containsKey(to))) {
                graph.put(to, new ArrayList<>());
            }
            graph.get(to).add(new Edge(to, w));
        }
    }

    public void removeEdge(String from, Edge e, Boolean isBi) {
        if (graph.containsKey(from)) {
            if (graph.get(from).contains(e)) {
                graph.get(from).remove(e);
            }
            if(isBi){
                graph.get(e.v).remove(new Edge(from,e.weight));
            }
        }
    }


}
