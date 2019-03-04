package search_and_pathfinding;

import java.util.ArrayList;

public class Node implements Comparable<Node> {
    int x;
    int y;
    int weight;
    ArrayList<Node> children;
    boolean visited = false;
    int totalCost;

    public Node() {

    }

    public Node(int x, int y, int weight) {
        this.x = x;
        this.y = y;
        this.weight = weight;
        children = new ArrayList<>();
    }

    public int compareTo(Node n) {
        return Integer.compare(this.totalCost, n.totalCost);
    }
}