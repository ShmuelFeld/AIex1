import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * this class extends Algorithm and implements the find path method using A* algorithm.
 */
public class AStarAlgorithm extends Algorithm {
    private PriorityQueue<Node> openList;

    /**
     * ctor
     */
    public AStarAlgorithm() {
        this.openList = new PriorityQueue<>();
    }

    /**
     * find path using A* algorithm.
     * @param root is the root node.
     * @param goal is the goal node.
     * @param matrix the class that represent the board state
     * @return a formatted string that represent the path from root to goal.
     */
    @Override
    public String findPath(Node root, Node goal, Matrix matrix) {
        int counter = 0;
        int max = (int)Math.pow(matrix.getSize(), 4);
        root.setG_score(0);
        this.openList.add(root);
        root.calculateEstimatedValue(goal);
        while (!this.openList.isEmpty() && counter <= max) {
            counter++;
            Node current = this.openList.poll();
            if(current.equals(goal)) {
                return this.toPath(current, root);
            }
            List<Node> arr = matrix.descendant(current);
            for (Node node: arr) {
                int costa = current.getG_score() + node.getCost();
                if(costa < node.getG_score()) {
                    node.setG_score(costa);
                    node.setFather(current);
                    node.calculateEstimatedValue(goal);
                    if (!this.openList.contains(node)) {
                        this.openList.add(node);
                    } else {
                        Iterator<Node> iter = this.openList.iterator();
                        while (iter.hasNext()) {
                            Node temp = iter.next();
                            if (node.equals(temp) && node.getF_score() < temp.getF_score()) {
                                this.openList.remove(temp);
                                this.openList.add(node);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return "no path";
    }
}
