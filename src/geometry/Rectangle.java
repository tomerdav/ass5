package geometry;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Guy Blachar
 * @author Tomer Davidor
 * @version 1.0
 */
public class Rectangle {
    private Point upperLeft;
    private Point lowerRight;
    private double width;
    private double height;

    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft the upper left point of the rectangle
     * @param width its width
     * @param height its height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        // Additional features
        this.lowerRight = new Point(upperLeft.getX() + width, upperLeft.getY()
                + height);
    }

    /**
     * Find the top line of the rectangle.
     * @return the top line of the rectangle
     */
    public Line topLine() {
        return new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.lowerRight.getX(), this.lowerRight.getY() - this.height);
    }

    /**
     * Find the bottom line of the rectangle.
     * @return the bottom line of the rectangle
     */
    public Line bottomLine() {
        return new Line(this.upperLeft.getX(), this.upperLeft.getY()
                + this.height, this.lowerRight.getX(), this.lowerRight.getY());
    }

    /**
     * Find the left line of the rectangle.
     * @return the left line of the rectangle
     */
    public Line leftLine() {
        return new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.lowerRight.getX() - this.width, this.lowerRight.getY());
    }

    /**
     * Find the right line of the rectangle.
     * @return the right line of the rectangle
     */
    public Line rightLine() {
        return new Line(this.upperLeft.getX() + this.width,
                this.upperLeft.getY(), this.lowerRight.getX(),
                this.lowerRight.getY());
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified
     * line.
     * @param line the line to check intersections with
     * @return a list of the intersection points
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersections = new LinkedList<Point>();
        // Create lines from the sides of the rectangle
        Line top = this.topLine();
        Line bottom = this.bottomLine();
        Line left = this.leftLine();
        Line right = this.rightLine();
        // Check collisions
        if (line.isIntersecting(top)) {
            intersections.add(line.intersectionWith(top));
        }
        if (line.isIntersecting(bottom)) {
            intersections.add(line.intersectionWith(bottom));
        }
        if (line.isIntersecting(left)) {
            intersections.add(line.intersectionWith(left));
        }
        if (line.isIntersecting(right)) {
            intersections.add(line.intersectionWith(right));
        }
        return intersections;
    }

    /**
     * Check if a point is in the rectangle.
     * @param point the point to check
     * @return true if the rectangle contains the point, else false
     */
    public boolean isContaining(Point point) {
        return point.getX() > this.upperLeft.getX()
                && point.getX() < this.lowerRight.getX()
                && point.getY() > this.upperLeft.getY()
                && point.getY() < this.lowerRight.getY();
    }

    /**
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    // Returns the upper-left point of the rectangle.
    /**
     * @return the upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return the lower right point of the rectangle
     */
    public Point getLowerRight() {
        return this.lowerRight;
    }
}
