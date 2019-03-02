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


        public abstract float getCost(AStarNode node);


        public abstract float getEstimatedCost(AStarNode node);


        public abstract List getNeighbors();
    }
}