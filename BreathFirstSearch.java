package pathfinding;

import javax.swing.plaf.nimbus.State;
import java.util.LinkedList;
import java.util.Map;

public class BreadthFirstSearch extends SearchAlgorithm {

    public BreadthFirstSearch(Map map) {
        super( map);
    }

    private LinkedList<search_and_pathfinding.Node> frontier;
    private search_and_pathfinding.Node currentNode = null;

    @Override
    public void setup() {
        search_and_pathfinding.Node startingNode = currentMap.getStart();
        frontier = new LinkedList<search_and_pathfinding.Node>();
        frontier.add(startingNode);
    }

    @Override
    public void loop() {
        if (frontier.isEmpty()) {
            currentState = State.FAILURE;
            return;
        }
        currentNode = frontier.pop();
        currentNode.isExplored = true;
        this.currentExpandedNodes++;
        this.currentNodesInMemory++; // The current node is in memory

        // Get the neighbors and add them to the frontier
        for (search_and_pathfinding.Node next : currentMap.neighborsAround(currentNode.position)) {
            this.currentNodesInMemory++; // Add Node next to memory count

            // Can only go to node if it's not explored and not blocked
            if (!next.isExplored && next.cost != 0) {
                next.parent = currentNode;

                // Check if we reached the goal
                if (next.equals(currentMap.getGoal())) {
                    currentNode = next;
                    currentState = State.SUCCESS;
                    break;
                }

                frontier.push(next);
            }
        }

        // Add the frontier nodes that are in memory
        this.currentNodesInMemory += frontier.size();
    }

    @Override
    public void finish() {
        // Calculate the final path cost
        int finalCost = 0;
        search_and_pathfinding.Node temp = currentNode;
        while (temp != null) {
            finalCost += temp.cost;
            temp = temp.parent;
        }

        this.currentPathCost = finalCost;
    }


    @Override
    public search_and_pathfinding.Node getGoalWithPath() {
        return currentNode;
    }
}