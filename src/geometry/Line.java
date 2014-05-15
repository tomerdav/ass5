package geometry;

import java.util.List;

/**
 * @author Tomer Davidor
 * @author Guy Blachar
 * @version 2.0
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Creating a line from the beginning and ending points.
     * @param start the beginning point of the line
     * @param end the ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    /**
     * Creating a line from the beginning and ending points' values.
     * @param x1 the beginning point's x value
     * @param y1 the beginning point's y value
     * @param x2 the ending point's x value
     * @param y2 the ending point's y value
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Calculating the length of the line.
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Calculating the middle point of the line.
     * @return the middle point of the line.
     */
    public Point middle() {
        double xMid = (this.start.getX() + this.end.getX()) / 2;
        double yMid = (this.start.getY() + this.end.getY()) / 2;
        return new Point(xMid, yMid);
    }

    /**
     * The function returns the start point of the line.
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * The function returns the end point of the line.
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Checking whether two lines are intersecting.
     * @param other another line to check with the current
     * @return true if the lines are intersecting, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) == null) {
            return false;
        }
        return true;
    }

    /**
     * Checking whether a point on the infinite line is in the limited line.
     * @param point the point to check.
     * @return true if the point is in the limits, false otherwise
     */
    public boolean isInLimits(Point point) {
        // The point is right to the line
        if ((point.getX() > this.start.getX()
                && point.getX() > this.end.getX())) {
            return false;
        }
        // The point is left to the line
        if ((point.getX() < this.start.getX()
                && point.getX() < this.end.getX())) {
            return false;
        }
        // The point is down to the line
        if ((point.getY() > this.start.getY()
                && point.getY() > this.end.getY())) {
            return false;
        }
        // The point is up to the line
        if ((point.getY() < this.start.getY()
                && point.getY() < this.end.getY())) {
            return false;
        }
        return true;
    }

    /**
     * Calculating the intersection point of two lines.
     * @param other another line to check with the current
     * @return the intersection point of the lines, and null if there isn't
     */
    public Point intersectionWith(Line other) {
        // Transforming current line to a*x+b*y=C
        double aCurrent = this.start.getY() - this.end.getY();
        double bCurrent = this.start.getX() - this.end.getX();
        double cCurrent = this.end.getX() * this.start.getY()
                - this.start.getX() * this.end.getY();
        // Transforming other line to a*x+b*y=C
        double aOther = other.start.getY() - other.end.getY();
        double bOther = other.start.getX() - other.end.getX();
        double cOther = other.end.getX() * other.start.getY()
                - other.start.getX() * other.end.getY();
        // Calculating the intersection point
        double det = aCurrent * bOther - aOther * bCurrent;
        if (det == 0) {
            return null;
        }
        double x = (bOther * cCurrent - bCurrent * cOther) / det;
        double y = -(aCurrent * cOther - aOther * cCurrent) / det;
        Point intersection = new Point(x, y);
        // Is the point in limits
        if (!this.isInLimits(intersection) || !other.isInLimits(intersection)) {
            return null;
        }
        return intersection;
    }

    /**
     * Comparing two lines.
     * @param other another line to compare to with the current one
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return this.start.equals(other.start) && this.end.equals(other.end);
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the
     * line.
     * @param rec the rectangle to find intersections with
     * @return the closest intersection point to the start of the line or null
     */
    public Point closestIntersectionToStartOfLine(Rectangle rec) {
        List<Point> intersections = rec.intersectionPoints(this);
        if (intersections.isEmpty()) {
            return null;
        }
        // Get closest point to start
        int index = 0, minIndex = 0;
        double minDistance = this.start.distance((Point) intersections
                .get(index));
        do {
            double dist = this.start.distance((Point) intersections.get(index));
            if (minDistance > dist) {
                minDistance = dist;
                minIndex = index;
            }
            index++;
        } while (index < intersections.size());
        // Return
        return (Point) intersections.get(minIndex);
    }
}
