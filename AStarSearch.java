package pathfinding;

import java.awt.*;

public class AStarSearch {

    public abstract class AStarNode implements Comparable {

        AStarNode pathParent;
        float costFromStart;
        float estimatedCostToGoal;


        public float getCost() {
            return costFromStart + estimatedCostToGoal;
        }


        public int compareTo(Object other) {
            float thisValue = this.getCost();
            float otherValue = ((AStarNode) other).getCost();

            float v = thisValue - otherValue;
            return (v > 0) ? 1 : (v < 0) ? -1 : 0; // sign function
        }
        public int manhattanDistance(int[] start, int[] goal){
            int distance = Math.abs(goal[0] - start[0]) + Math.abs(goal[1] - start[1]);
            return distance;
        }
        public int pickHeuristic(String heuristic, int[] start, int[] goal) {
            if(heuristic.equalsIgnoreCase("Manhattan"))
                return manhattanDistance(start,goal);
            else
                return 0;
        }


        public abstract float getCost(AStarNode node);


        public abstract float getEstimatedCost(AStarNode node);


        public abstract List getNeighbors();
    }
}
