/**
 * point class
 */
public class Point {
    private int x;
    private int y;

    /**
     * ctor
     * @param x dimension
     * @param y dimension
     */
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * getter for Y value.
     * @return the Y value
     */
    public int getY() {
        return y;
    }

    /**
     * getter for X value.
     * @return the X value
     */
    public int getX() {
        return x;
    }
}
