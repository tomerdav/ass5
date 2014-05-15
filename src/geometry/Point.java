package geometry;

/**
 * @author Tomer Davidor
 * @author Guy Blachar
 * @version 1.0
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructor.
     * @param x the x value of the point
     * @param y the y value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculating the distance between two points.
     * @param other another point to find its distance from the current
     * @return the distance between the points
     */
    public double distance(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Comparing two points.
     * @param other another point to compare to with the current one
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        // Check if null
        return (other != null) && (this.x == other.x) && (this.y == other.y);
    }

    /**
     * The function returns the x value of the point.
     * @return the x value of the point
     */
    public double getX() {
        return this.x;
    }

    /**
     * The function returns the y value of the point.
     * @return the y value of the point
     */
    public double getY() {
        return this.y;
    }
}
