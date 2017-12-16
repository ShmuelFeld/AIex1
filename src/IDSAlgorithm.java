
import java.util.HashSet;
import java.util.List;

/**
 *  this class extends Algorithm and implements the find path method using IDS algorithm.
 */
public class IDSAlgorithm extends Algorithm {
    private String found;
    private Node root;
    private Node goal;
    private Node wanted;
    private Matrix matrix;
    private HashSet<Node> set;
    /**
     *ctor
     */
    IDSAlgorithm(){
    }

    /**
     * find path using IDS algorithm.
     * @param root is the root node.
     * @param goal is the goal node.
     * @param matrix the class that represent the board state
     * @return a formatted string that represent the path from root to goal.
     */
    @Override
    public String findPath(Node root, Node goal, Matrix matrix) {
        this.matrix = matrix;
        this.goal = goal;
        this.root = root;
        int lim = this.matrix.getSize() * 2;
        for (int i = 0; i < lim; i++) {
//            this.set = new HashSet<>();
//            this.set.add(this.root);
            Node n = ids(this.root, i);
            found = toPath(n, root);
            if (found != null)
                return found;
//            this.set.clear();
//            this.set = null;
        }
        return "no path";
    }
    private Node ids(Node node, int depth) {
        if (depth == 0 && node.equals(this.goal)) {
            return node;
        }
        else if (depth > 0) {
            List<Node> li = node.myDescendants();
            for (Node n : li) {
//                if(!this.set.contains(n)) {
                    this.wanted = this.ids(n, --depth);
//                    this.set.add(n);
                    depth++;
//                }
                if (this.wanted != null) {
                    return this.wanted;
                }
            }
        }
        else
            return null;
        return null;
    }
}
