import java.util.Comparator;
import java.util.List;

/**
 * represent each node in the graph.
 */
public class Node implements Comparator<Node>, Comparable<Node> {
    private Node father;
    private char kind;
    private Point location;
    private Matrix matrix;
    private int creationTime;
    private int g_score;
    private int f_score;

    /**
     *
     * @param location X&Y dimensions
     * @param matrix the board
     * @param kind the kind of the node
     * @param creationTime the creation time of the node
     */
    public Node(Point location, Matrix matrix, char kind, int creationTime) {
        this.location = location;
        this.matrix = matrix;
        this.kind = kind;
        this.creationTime = creationTime;
        this.g_score = 100;
        this.f_score = 100;
    }

    /**
     * getter for the f function
     * @return F value.
     */
    public int getF_score() {
        return f_score;
    }

    /**
     * calculate the f function
     * @param goal is the goal node
     */
    public void calculateEstimatedValue(Node goal) {
        this.f_score = this.heuristic(goal) + this.getG_score();
    }

    /**
     * the heuristic function for A*
     * @param goal is the goal node
     * @return the maximum value between the difference of y and x
     */
    public int heuristic(Node goal) {
        return Math.max(Math.abs(this.getLocation().getX() - goal.getLocation().getX())
                , Math.abs(this.getLocation().getY() - goal.getLocation().getY()));
    }

    /**
     * getter for the real value from root to me.
     * @return the real value from root to me.
     */
    public int getG_score() {
        return this.g_score;
    }

    /**
     * setter for the real value from root to me.
     * @param cost the real value.
     */
    public void setG_score(int cost) {
        this.g_score = cost;
    }

    /**
     * getter for creation time
     * @return creation time
     */
    public int getCreationTime() {

        return creationTime;
    }

    /**
     * getter for the father
     * @return the father
     */
    public Node getFather() {
        return father;
    }

    /**
     * setter for the father
     * @param father is the father
     */
    public void setFather(Node father) {
        this.father = father;
    }

    /**
     * getter for the cost to go from my father to me.
     * @return the real cost.
     */
    public int getCost() {
        switch (this.getKind()) {
            case 'R':
                return 1;
            case 'H':
                return 10;
            case 'D':
                return 3;
            default:
                return 0;

        }
    }

    /**
     * getter for the node's kind
     * @return the kind
     */
    private char getKind() {
        return kind;
    }

    /**
     * getter for node's location
     * @return the location.
     */
    public Point getLocation() {
        return location;
    }

    /**
     * overrides compare method for the priority queue by the F value.
     * @param node first object(node)
     * @param t1 second object(node)
     * @return negative number if o1 is smaller, otherwise positive.
     * if n1.getF_score() - n2.getF_score() = 0 so the priority queue is order them by their creation time
     */
    @Override
    public int compare(Node node, Node t1) {
        int f = node.getF_score() - t1.getF_score();
        if (f != 0) {
            return f;
        } else {
            return t1.getCreationTime() - node.getCreationTime();
        }
    }

    /**
     * overrides equals for using array list.
     * @param obj is the object we want to compare with
     * @return true if equals. false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        // If the object is compared with itself then return true
        if (obj == this) {
            return true;
        }
        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(obj instanceof Node)) {
            return false;
        }
        Node n = (Node) obj;
        return ((this.location.getX() == n.getLocation().getX())
                &&(this.location.getY() == n.getLocation().getY()));
    }

    /**
     * @return the descendants of the node.
     */
    public List<Node> myDescendants() {
        return this.matrix.descendant(this);
    }

    /**
     * @return a string that represent the way from my father to me.
     */
    public String fromFatherToMe() {
        if (this.getLocation().getX() - 1 == this.getFather().getLocation().getX()
                && this.getLocation().getY() == this.getFather().getLocation().getY()) {
            return "R";
        }
        if (this.getLocation().getX() - 1 == this.getFather().getLocation().getX()
                && this.getLocation().getY() - 1 == this.getFather().getLocation().getY()) {
            return "DR";
        }
        if (this.getLocation().getX() == this.getFather().getLocation().getX()
                && this.getLocation().getY() - 1 == this.getFather().getLocation().getY()) {
            return "D";
        }
        if (this.getLocation().getX() + 1 == this.getFather().getLocation().getX()
                && this.getLocation().getY() - 1 == this.getFather().getLocation().getY()) {
            return "DL";
        }
        if (this.getLocation().getX() + 1 == this.getFather().getLocation().getX()
                && this.getLocation().getY() == this.getFather().getLocation().getY()) {
            return "L";
        }
        if (this.getLocation().getX() + 1 == this.getFather().getLocation().getX()
                && this.getLocation().getY() + 1 == this.getFather().getLocation().getY()) {
            return "UL";
        }
        if (this.getLocation().getX() == this.getFather().getLocation().getX()
                && this.getLocation().getY() + 1 == this.getFather().getLocation().getY()) {
            return "U";
        }
        if (this.getLocation().getX() - 1 == this.getFather().getLocation().getX()
                && this.getLocation().getY() + 1 == this.getFather().getLocation().getY()) {
            return "UR";
        }
        return null;
    }

    /**
     * prints the node.
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(this.location.getX());
        sb.append(",");
        sb.append(this.location.getY());
        sb.append(")");
        return sb.toString();
    }

    /**
     * overrides compare method for the priority queue.
     * @param node first object(node)
     * @return negative number if o1 is smaller, otherwise positive.
     * if n1.getF_score() - n2.getF_score() = 0 so the priority queue is order them by their creation time
     */
    @Override
    public int compareTo(Node node) {
        return this.compare(this, node);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
