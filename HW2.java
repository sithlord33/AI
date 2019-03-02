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
        int[][] map = readFile(path);
        Node[] graph = new Node[map.length * map[0].length];
        buildGraph(map, start);
    }

    private static int[][] readFile(String arg) {
        int[][] map = new int[0][];
        try (BufferedReader br = new BufferedReader(new FileReader(arg))) {
            start = new int[2];
            goal = new int[2];

            String dimString = br.readLine();
            String[] split = dimString.split(" ");
            map = new int[Integer.parseInt(split[0])][Integer.parseInt(split[1])];

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
                    map[i][j] = Integer.parseInt(split[j]);
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static Node buildGraph(int[][] map, int[] start) {
        int x = start[1];
        int y = start[0];
        int width = map.length;
        boolean[] graphed = new boolean[map.length * map[0].length];
        boolean all = false;

        Node head = new Node(x, y, map[y][x]);
        graphed[y + x * width] = true;
        Node aux = new Node();
        aux = head;

        findChildren(map, x, y, aux);

        Queue<Node> queue = new LinkedList<>();
        while (!areAllTrue(graphed)) {
            for (Node c : aux.children)
                queue.add(c);
            for (Node c : queue){
                aux = c;
                queue.remove();
                x = aux.x;
                y = aux.y;
                if (graphed[y + x * width])
                    break;
                graphed[y + x * width] = true;
                findChildren(map, x, y, aux);
            }
        }
        return head;
    }

    public static void findChildren(int[][] map, int x, int y, Node aux) {
        if (x - 1 < 0 && y - 1 < 0) {
            aux.children.add(new Node(x + 1, y, map[y][x + 1]));
            aux.children.add(new Node(x, y + 1, map[y + 1][x]));
        } else if (x + 1 < 0 && y - 1 < 0) {
            aux.children.add(new Node(x - 1, y, map[y][x - 1]));
            aux.children.add(new Node(x, y + 1, map[y + 1][x]));
        } else if (x - 1 < 0 && y + 1 < 0) {
            aux.children.add(new Node(x + 1, y, map[y][x + 1]));
            aux.children.add(new Node(x, y - 1, map[y - 1][x]));
        } else if (x + 1 < 0 && y + 1 < 0) {
            aux.children.add(new Node(x - 1, y, map[y][x - 1]));
            aux.children.add(new Node(x, y - 1, map[y - 1][x]));
        } else if (x - 1 < 0) {
            aux.children.add(new Node(x + 1, y, map[y][x + 1]));
            aux.children.add(new Node(x, y - 1, map[y - 1][x]));
            aux.children.add(new Node(x, y + 1, map[y + 1][x]));
        } else if (x + 1 < 0) {
            aux.children.add(new Node(x - 1, y, map[y][x - 1]));
            aux.children.add(new Node(x, y - 1, map[y - 1][x]));
            aux.children.add(new Node(x, y + 1, map[y + 1][x]));
        } else if (y - 1 < 0) {
            aux.children.add(new Node(x - 1, y, map[y][x - 1]));
            aux.children.add(new Node(x + 1, y, map[y][x + 1]));
            aux.children.add(new Node(x, y + 1, map[y + 1][x]));
        } else if (y + 1 < 0) {
            aux.children.add(new Node(x - 1, y, map[y][x - 1]));
            aux.children.add(new Node(x + 1, y, map[y][x + 1]));
            aux.children.add(new Node(x, y - 1, map[y - 1][x]));
        } else {
            aux.children.add(new Node(x - 1, y, map[y][x - 1]));
            aux.children.add(new Node(x + 1, y, map[y][x + 1]));
            aux.children.add(new Node(x, y - 1, map[y - 1][x]));
            aux.children.add(new Node(x, y + 1, map[y + 1][x]));
        }
    }

    private static boolean areAllTrue(boolean[] graphed) {
        for (boolean b : graphed)
            if (!b)
                return false;
        return true;
    }

    public static void auxBuild(Node head, int[][] map) {

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
