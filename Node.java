package search_and_pathfinding;

import java.util.ArrayList;

public class Node {
    int x;
    int y;
    int weight;
    ArrayList<Node> children;

    public Node() {

    }

    public Node(int x, int y, int weight) {
        this.x = x;
        this.y = y;
        this.weight = weight;
        children = new ArrayList<>();
    }

}