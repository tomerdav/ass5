package core;

import geometry.Point;

/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 * @author Tomer Davidor
 * @author Guy Blachar
 * @version 1.0
 */
public class Velocity {
    private double dx;
    private double dy;
    /**
     * Stands for the difference between the normal angles and the angles
     * specified in the assignment (up is 0 degrees, negative y).
     */
    public static final double ROTATION_OF_AXES = 270;

    /**
     * Constructor, Cartesian.
     * @param dx how many units to move in x direction
     * @param dy how many units to move in y direction
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * A static constructor for velocity.
     * @param angle the velocity vector's angle, 0 is up
     * @param speed how many units to move in that direction
     * @return a velocity with the appropriate dx and dy
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.round(speed
                * Math.cos(Math.toRadians(angle + ROTATION_OF_AXES)));
        double dy = Math.round(speed
                * Math.sin(Math.toRadians(angle + ROTATION_OF_AXES)));
        return new Velocity(dx, dy);
    }

    /**
     * @return the dx
     */
    public double getDx() {
        return dx;
    }

    /**
     * @return the dy
     */
    public double getDy() {
        return dy;
    }

    /**
     * Returns the speed of the velocity.
     * @return sqrt((dx + dy)^2)
     */
    public double getSpeed() {
        return Math.hypot(dx, dy);
    }

    /**
     * Returns the angle of the velocity.
     * @return atan(dy/dx) - 270 degrees
     */
    public double getAngle() {
        // Get the angle in radians
        double theta = Math.atan2(dy, dx);
        // Convert into scale of positive degrees only
        double angle = Math.toDegrees(theta);
        // Rotate the angle into the desired axes
        angle -= ROTATION_OF_AXES;
        // Convert back into positive degrees only
        if (angle < 0) {
            angle += 360;
        }
        return angle;
    }

    /**
     * Take a point with position (x, y) and return a new point with position (x
     * + dx, y + dy).
     * @param p the point to move
     * @return a new point in the wanted location
     */
    public Point applyToPoint(Point p) {
        double movedX = p.getX() + this.dx;
        double movedY = p.getY() + this.dy;
        return new Point(movedX, movedY);
    }

    /**
     * Compares two velocities.
     * @param velocity a velocity to compare to
     * @return true if the velocities are equal, else false
     */
    public boolean equals(Velocity velocity) {
        return this.dx == velocity.dx && this.dy == velocity.dy;
    }
}
