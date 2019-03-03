package search_and_pathfinding;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author Ricardo Jimenez Todd
 * @author Jose Luis Lujan
 * @version 1.1
 */
public class HW2 {
    private static int[] start;
    private static int[] goal;

    public static void main(String[] args) {
        String path = "C:\\Users\\ricar\\eclipse-workspace\\Artificial_Intelligence\\src\\search_and_pathfinding\\myMap.txt";
        Node[][] map = readFile(path);
        buildGraph(map);
        Node startNode = map[start[0]][start[1]];
        Node goalNode = map[goal[0]][goal[1]];


        //BFS(startNode, goalNode);
        System.out.println();
        IDS(startNode, goalNode);
        AS(startNode, goalNode);
    }

    /**
     * Reads a text file of specific format to transform into a Node graph so our search algorithms can find paths through it
     * <b>
     * First line read will be the dimensions of our map
     * Second line is the starting coordinates of our pathfinding
     * Third line is the goal our algorithm has to reach
     * Rest of the file is the map we are going to search through
     * </b>
     *
     * @param arg text file
     * @return Node[][] map
     */
    private static Node[][] readFile(String arg) {
        Node[][] map = new Node[0][];
        try (BufferedReader br = new BufferedReader(new FileReader(arg))) {
            start = new int[2];
            goal = new int[2];

            String dimString = br.readLine();
            String[] split = dimString.split(" ");
            map = new Node[Integer.parseInt(split[0])][Integer.parseInt(split[1])];

            String startString = br.readLine();
            String[] startSplit = startString.split(" ");
            String goalString = br.readLine();
            String[] goalSplit = goalString.split(" ");

            for (int i = 0; i < startString.length() - 1; i++) {
                start[i] = Integer.parseInt(startSplit[i]);
                goal[i] = Integer.parseInt(goalSplit[i]);
            }

            String line;
            for (int i = 0; (line = br.readLine()) != null; i++) {
                split = line.split(" ");
                for (int j = 0; j < split.length; j++) {
                    map[i][j] = new Node(i, j, Integer.parseInt(split[j]));
                    if (map[i][j].weight == 0)
                        map[i][j].visited = true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * Joins all the nodes with their respective children
     * <b>
     * Takes a Node map and joins all the nodes together
     * </b>
     *
     * @param map 2D array of Nodes
     */
    public static void buildGraph(Node[][] map) {
        int width = map.length;
        int height = map[0].length;

        for (int i = 0; i < width - 1; i++)
            for (int j = 0; j < height - 1; j++)
                findChildren(map, map[i][j]);
    }

    /**
     * Helper method to generate successor nodes
     *
     * @param map 2D array of Nodes
     * @param aux Current node
     */
    public static void findChildren(Node[][] map, Node aux) {
        int x = aux.x;
        int y = aux.y;
        if (x != 0)
            aux.children.add(map[x - 1][y]);
        if (x != map.length - 1)
            aux.children.add(map[x + 1][y]);
        if (y != 0)
            aux.children.add(map[x][y - 1]);
        if (y != map[0].length - 1)
            aux.children.add(map[x][y + 1]);
    }

    public static void BFS(Node startNode, Node goalNode) {
        Queue<Node> q = new LinkedList<>();

        double tStart = System.currentTimeMillis();
        q.add(startNode);
        startNode.visited = true;

        while (!q.isEmpty()) {
            Node aux = q.remove();
            System.out.println("x = " + aux.x + " y = " + aux.y);
            if (aux.equals(goalNode)) {
                System.out.println("Goal Node Found!");
                break;
            }
            for (Node c : aux.children)
                if (c.weight != 0 && !c.visited) {
                    c.visited = true;
                    q.add(c);
                }
        }

        double tEnd = System.currentTimeMillis();
        double tTotal = tEnd - tStart;
        System.out.println("It took " + tTotal + " milliseconds");
    }

    public static void IDS(Node startNode, Node goalNode) {
        Stack<Node> s = new Stack<>();

        double tStart = System.currentTimeMillis();
        s.push(startNode);
        startNode.visited = true;

        while (!s.isEmpty()) {
            Node aux = s.pop();
            System.out.println("x = " + aux.x + " y = " + aux.y);
            if (aux.equals(goalNode)) {
                System.out.println("Goal Node Found!");
                break;
            }
            for (Node c : aux.children)
                if (c.weight != 0 && !c.visited) {
                    c.visited = true;
                    s.push(c);
                }
        }

        double tEnd = System.currentTimeMillis();
        double tTotal = tEnd - tStart;
        System.out.println("It took " + tTotal + " milliseconds");

    }

    public static void AS(Node startNode, Node goalNode) {
    }

    public static int manhattanDistance(int[] start, int[] goal) {
        int distance = Math.abs(goal[0] - start[0]) + Math.abs(goal[1] - start[1]);
        return distance;
    }
}
