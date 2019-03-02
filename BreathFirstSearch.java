package pathfinding;

import search_and_pathfinding.Node;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class BFS {
    public LinkedList constructPath(Node node) {
        LinkedList path = new LinkedList();
        while (node.Parent != null) {
            path.addFirst( node );
            node = (Node) node.Parent;
        }
        return path;
    }

    public List search(search_and_pathfinding.Node startNode, search_and_pathfinding.Node goalNode) {
        // list of visited nodes
        LinkedList closedList = new LinkedList();

        // list of nodes to visit (sorted)
        LinkedList openList = new LinkedList();
        openList.add( startNode );
        startNode.Parent = null;

        while (!openList.isEmpty()) {
            search_and_pathfinding.Node node = (search_and_pathfinding.Node) openList.removeFirst();
            if (node == goalNode) {
                // path found!
                Node goal;
                return constructPath( goal );
            } else {
                closedList.add( node );

                // add neighbors to the open list
                Iterator i = node.neighbors.iterator();
                while (i.hasNext()) {
                    Node neighborNode = (Node) i.next();
                    if (!closedList.contains( neighborNode ) &&
                            !openList.contains( neighborNode )) {
                        neighborNode.Parent = node;
                        openList.add( neighborNode );
                    }
                }
            }
        }

        // no path found
        return null;
    }
}
