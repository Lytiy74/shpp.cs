package com.shpp.p2p.cs.azaika.assignment12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Graph {
    private int maxSize;
    private HashMap<Pixel, List<Pixel>> nodes;


    public Graph(int maxSize) {
        this.maxSize = maxSize;
        nodes = new HashMap<>();
    }
    public void addVertex(Pixel pixel){
        nodes.putIfAbsent(pixel, new ArrayList<>());
    }

    public void addEdge(Pixel v, Pixel w) {
        if (nodes.containsKey(v)) {
            nodes.get(v).add(w);
        } else {
            nodes.put(v, List.of(w));
        }
    }

    public int getSize() {
        return maxSize;
    }

    public HashMap<Pixel, List<Pixel>> getNodes() {
        return nodes;
    }

    public void setNodes(HashMap<Pixel, List<Pixel>> nodes) {
        this.nodes = nodes;
    }

    public boolean isEmpty() {
        return nodes.isEmpty();
    }

    public void clear() {
        nodes.clear();
    }

    public boolean contains(Pixel v) {
        return nodes.containsKey(v);
    }

    public List<Pixel> getNeighbors(Pixel v) {
        return nodes.getOrDefault(v, List.of());
    }

    public void removeEdge(Pixel v, Pixel w) {
        if (nodes.containsKey(v)) {
            nodes.get(v).remove(w);
        }
    }

    public void removeNode(Pixel v) {
        nodes.remove(v);
        for (List<Pixel> neighbors : nodes.values()) {
            neighbors.remove(v);
        }
    }

    public void printGraph() {
        for (Pixel pixel : nodes.keySet()) {
            System.out.print(pixel + " -> ");
            for (Pixel neighbor : nodes.get(pixel)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

}
