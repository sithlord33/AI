package search_and_pathfinding;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HW2 {
    public static void main(String[] args) {
        String path = "C:\\Users\\ricar\\eclipse-workspace\\Artificial_Intelligence\\src\\search_and_pathfinding\\myMap.txt";
        int[][] map = readFile(path);
    }

    private static int[][] readFile(String arg) {
        int[][] map = new int[0][];
        try (BufferedReader br = new BufferedReader(new FileReader(arg))) {
            int[] start = new int[2];
            int[] goal = new int[2];

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


}
