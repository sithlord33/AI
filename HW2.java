package search_and_pathfinding;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HW2 {
    private static int[] start;
    private static int[] goal;

    public static void main(String[] args) {
        String path = "C:\\Users\\ricar\\eclipse-workspace\\Artificial_Intelligence\\src\\search_and_pathfinding\\myMap.txt";
        Node[][] map = readFile(path);
        buildGraph(map, start);
    }

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
            int i = 0;
            while ((line = br.readLine()) != null) {
                split = line.split(" ");
                for (int j = 0; j < split.length; j++)
                    map[i][j] = new Node(i, j, Integer.parseInt(split[j]));
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static Node buildGraph(Node[][] map, int[] start) {
        int x = start[0];
        int y = start[1];
        int width = map.length;
        int size = map.length * map[0].length;
        boolean[] graphed = new boolean[size];

        Node head = map[x][y];
        Node aux = head;

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(aux);
        while (!queue.isEmpty()) {
            aux = queue.remove();
            if (!graphed[aux.x + aux.y * width]) {
                findChildren(map, aux);
                graphed[aux.x + aux.y * width] = true;
                for (Node c : aux.children)
                    if (!graphed[c.x + c.y * width])
                        queue.add(c);
            }
        }
        return head;
    }

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

    public static void BFS(Node[] map) {

    }

    public static void IDS(int[][] map) {
        boolean[] visited = new boolean[map.length * map[0].length];
    }

    public static void AS(int[][] map) {
        boolean[] visited = new boolean[map.length * map[0].length];
    }

}
