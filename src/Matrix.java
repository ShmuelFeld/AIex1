import java.util.ArrayList;
import java.util.List;

/**
 * this class holds the state of the board.
 */
public class Matrix {
    private String state;
    private int size;
    private char[][] mat;
    private Node root;
    private Node goal;
    private int creationTime;

    /**
     * getter for size of board
     * @return the size
     */
    public int getSize() {
        return this.size;
    }

    /**
     * getter for the root node
     * @return the root node
     */
    public Node getRoot() {
        return this.root;
    }

    /**
     * getter for the goal node
     * @return the goal node
     */
    public Node getGoal() {
        return this.goal;
    }

    /**
     * constructor
     * @param state string that represent the state
     * @param size of board
     */
    public Matrix(String state, int size) {
        this.state = state;
        this.size = size;
        this.creationTime = 0;
        this.setBoard(this.state.toCharArray(), this.size);
        this.setRootAndGoal();
    }

    /**
     * sets the matrix
     * @param state char array of the state
     * @param size of the board
     */
    public void setBoard(char[] state, int size) {
        this.mat = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.mat[i][j] = state[(size * i) + j];
            }
        }
    }

    /**
     * sets the root and the goal.
     */
    public void setRootAndGoal() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.mat[i][j] == 'S') {
                    this.root = new Node(new Point(i,j), this, 'S', this.creationTime);
                    this.creationTime++;
                    this.root.setFather(null);
                }else if (this.mat[i][j] == 'G') {
                    this.goal = new Node(new Point(i,j), this, 'G', this.creationTime);
                    this.creationTime++;
                }
            }

        }
    }

    /**
     * returns the char at a specific place
     * @param x dimension
     * @param y dimension
     * @return character
     */
    private char valueAt(int x, int y) {
        return this.state.charAt(size * y + x);
    }

    /**
     * check if ancestor have a right son, if he does create and returns it
     * @param ancestor current node
     * @return node's descendant
     */

    private Node RSon(Node ancestor) {
        int x = ancestor.getLocation().getX() + 1;
        int y = ancestor.getLocation().getY();
        if (validNode(x, y, ancestor)) {
            return nodeCreator(x, y);
        }else
            return null;
    }
    /**
     * check if ancestor have a right down son, if he does create and returns it
     * @param ancestor current node
     * @return node's descendant
     */
    private Node RDSon(Node ancestor) {
        int x = ancestor.getLocation().getX() + 1;
        int y = ancestor.getLocation().getY() + 1;
        if (validNode(x, y, ancestor) && this.siblingIsWater(x, y - 1)
                && this.siblingIsWater(x - 1, y)) {
            return nodeCreator(x, y);
        }else
            return null;
    }
    /**
     * check if ancestor have a down son, if he does create and returns it
     * @param ancestor current node
     * @return node's descendant
     */
    private Node DSon(Node ancestor) {
        int x = ancestor.getLocation().getX();
        int y = ancestor.getLocation().getY() + 1;
        if (validNode(x, y, ancestor)) {
            return nodeCreator(x, y);
        }else
            return null;
    }
    /**
     * check if ancestor have a left son, if he does create and returns it
     * @param ancestor current node
     * @return node's descendant
     */
    private Node LSon(Node ancestor) {
        int x = ancestor.getLocation().getX() - 1;
        int y = ancestor.getLocation().getY();
        if (validNode(x, y, ancestor)) {
            return nodeCreator(x, y);
        }else
            return null;
    }
    /**
     * check if ancestor have a left up son, if he does create and returns it
     * @param ancestor current node
     * @return node's descendant
     */
    private Node LUSon(Node ancestor) {
        int x = ancestor.getLocation().getX() - 1;
        int y = ancestor.getLocation().getY() - 1;
        if (validNode(x, y, ancestor) && this.siblingIsWater(x, y + 1)
                && this.siblingIsWater(x + 1, y)) {
            return nodeCreator(x, y);
        }else
            return null;
    }
    /**
     * check if ancestor have a up son, if he does create and returns it
     * @param ancestor current node
     * @return node's descendant
     */
    private Node USon(Node ancestor) {
        int x = ancestor.getLocation().getX();
        int y = ancestor.getLocation().getY() - 1;
        if (validNode(x, y, ancestor)) {
            return nodeCreator(x, y);
        }else
            return null;
    }
    /**
     * check if ancestor have a right up son, if he does create and returns it
     * @param ancestor current node
     * @return node's descendant
     */
    private Node RUSon(Node ancestor) {
        int x = ancestor.getLocation().getX() + 1;
        int y = ancestor.getLocation().getY() - 1;
        if (validNode(x, y, ancestor) && this.siblingIsWater(x, y + 1)
                && this.siblingIsWater(x - 1, y)) {
            return nodeCreator(x, y);
        }else
            return null;
    }
    /**
     * check if ancestor have a left down son, if he does create and returns it
     * @param ancestor current node
     * @return node's descendant
     */
    private Node LDSon(Node ancestor) {
        int x = ancestor.getLocation().getX() - 1;
        int y = ancestor.getLocation().getY() + 1;
        if (validNode(x, y, ancestor) && this.siblingIsWater(x, y - 1)
                && this.siblingIsWater(x + 1, y)) {
            return nodeCreator(x, y);
        }else
            return null;
    }

    /**
     * creates new nodes
     * @param x dimension
     * @param y dimension
     * @return node with the given values.
     */
    private Node nodeCreator(int x, int y) {
        Node n = new Node(new Point(x, y), this, this.valueAt(x, y), this.creationTime);
        this.creationTime++;
        return n;
    }

    /**
     * check whether this node is valid
     * @param x dimension
     * @param y dimension
     * @param father
     * @return true if valid, otherwise false.
     */
    private boolean validNode(int x, int y, Node father) {
        if (x < 0 || x >= this.size || y < 0 || y >= this.size
                || (this.root.getLocation().getX() == x && this.getRoot().getLocation().getY() == y)) {
            return false;
        }else if (father.getLocation().getX() == x && father.getLocation().getY() == y) {
            return false;
        }

        return !(valueAt(x,y) == 'W');
    }

    /**
     * check whether if there is descendant in slant
     * @param x dimension
     * @param y dimension
     * @return true if  not valid, otherwise false.
     */
    private boolean siblingIsWater(int x, int y) {
        return (this.valueAt(x, y) != 'W');
    }

    /**
     * creates all the descendant for a given node
     * @param n the given node
     * @return array with all the descendant
     */
    public List<Node> descendant(Node n){
        ArrayList<Node> descendant = new ArrayList<Node>();
        Node r = this.RSon(n);
        if (r != null) {
            descendant.add(r);
            this.myFather(r, n);
        }
        Node rd = this.RDSon(n);
        if (rd != null) {
            descendant.add(rd);
            this.myFather(rd, n);
        }
        Node d = this.DSon(n);
        if (d != null) {
            descendant.add(d);
            this.myFather(d, n);
        }
        Node ld = this.LDSon(n);
        if (ld != null) {
            descendant.add(ld);
            this.myFather(ld, n);
        }
        Node l = this.LSon(n);
        if (l != null) {
            descendant.add(l);
            l.setFather(n);
        }
        Node lu = this.LUSon(n);
        if (lu != null) {
            descendant.add(lu);
            this.myFather(lu, n);
        }
        Node u = this.USon(n);
        if (u != null) {
            descendant.add(u);
            this.myFather(u, n);
        }
        Node ru = this.RUSon(n);
        if (ru != null) {
            descendant.add(ru);
            this.myFather(ru, n);
        }
        return descendant;
    }
    private void myFather(Node son, Node father) {
       son.setFather(father);
    }

}
