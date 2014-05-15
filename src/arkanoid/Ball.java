package arkanoid;

import biuoop.DrawSurface;

import core.CollisionInfo;
import core.Sprite;
import core.Velocity;

import geometry.Line;
import geometry.Point;

import java.awt.Color;

/**
 * @author Tomer Davidor
 * @author Guy Blachar
 * @version 2.0
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private int surfaceWidth;
    private int surfaceHeight;
    private GameEnvironment environment;

    /**
     * Constructor from point.
     * @param center the center of the ball
     * @param r the ball's size
     * @param color its color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this(center, r, color, 0, 0);
    }

    /**
     * Constructor from coordinates.
     * @param x the x value for center
     * @param y the y value for center
     * @param r the ball's size
     * @param color its color
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * Constructor from point and sizes.
     * @param center the center of the ball
     * @param r the ball's size
     * @param color its color
     * @param surfaceWidth the DrawSurface's width
     * @param surfaceHeight the DrawSurdace's height
     */
    public Ball(Point center, int r, java.awt.Color color, int surfaceWidth,
            int surfaceHeight) {
        this(center, r, color, new Velocity(0, 0), surfaceWidth, surfaceHeight);
    }

    /**
     * Constructor from (integer) coordinates and sizes.
     * @param x the x value of the center
     * @param y the y value of the center
     * @param r the balls' radius
     * @param color its color
     * @param surfaceWidth the DrawSurface's width
     * @param surfaceHeight the DrawSurdace's height
     */
    public Ball(int x, int y, int r, java.awt.Color color, int surfaceWidth,
            int surfaceHeight) {
        this(new Point(x, y), r, color, surfaceWidth, surfaceHeight);
    }

    /**
     * Constructor with velocity and surface measures.
     * @param center the center of the ball
     * @param radius the balls' radius
     * @param color its color
     * @param velocity the starting velocity
     * @param surfaceWidth the DrawSurface's width
     * @param surfaceHeight the DrawSurdace's height
     */
    public Ball(Point center, int radius, java.awt.Color color,
            Velocity velocity, int surfaceWidth, int surfaceHeight) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.velocity = velocity;
        this.surfaceWidth = surfaceWidth;
        this.surfaceHeight = surfaceHeight;
    }

    /**
     * Constructor with velocity and surface measures.
     * @param x the x value of the center
     * @param y the y value of the center
     * @param r the balls' radius
     * @param color its color
     * @param velocity the starting velocity
     * @param surfaceWidth the DrawSurface's width
     * @param surfaceHeight the DrawSurdace's height
     */
    public Ball(int x, int y, int r, java.awt.Color color, Velocity velocity,
            int surfaceWidth, int surfaceHeight) {
        this(new Point(x, y), r, color, velocity, surfaceWidth, surfaceHeight);
    }

    /**
     * @return the x value of the center, as integer.
     */
    public int getX() {
        return (int) Math.round(this.center.getX());
    }

    /**
     * @return the y value of the center, as integer.
     */
    public int getY() {
        return (int) Math.round(this.center.getY());
    }

    /**
     * @return the size of the ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return the ball's color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Sets the ball's velocity.
     * @param v the new velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the ball's velocity.
     * @param dx the new velocity's dx
     * @param dy the new velocity's dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * @return the ball's velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * @return the surfaceWidth
     */
    public int getSurfaceWidth() {
        return surfaceWidth;
    }

    /**
     * @return the surfaceHeight
     */
    public int getSurfaceHeight() {
        return surfaceHeight;
    }

    /**
     * Checks if the ball will remain within the height boundaries.
     * @return true if the ball will leave the top or bottom, false otherwise
     */
    public boolean isOutY() {
        // Get its current distances from the edges
        double topOfBall = this.center.getY() - this.radius;
        double bottomOfBall = this.center.getY() + this.radius;
        // Check if on its next move, the ball will get out of the surface
        return topOfBall + this.velocity.getDy() <= 0
                || bottomOfBall + this.velocity.getDy() >= this.surfaceHeight;
    }

    /**
     * Checks if the ball will remain within the width boundaries.
     * @return true if the ball will leave the left or right, false otherwise
     */
    public boolean isOutX() {
        // Get its current distances from the edges
        double leftOfBall = this.center.getX() - this.radius;
        double rightOfBall = this.center.getX() + this.radius;
        // Check if on its next move, the ball will get out of the surface
        return leftOfBall + this.velocity.getDx() <= 0
                || rightOfBall + this.velocity.getDx() >= this.surfaceWidth;
    }

    /**
     * Reverts the ball's velocity if needed.
     * @return TRUE if the ball bounced, FALSE otherwise public boolean
     *         bounceEdgeOfScreen() { boolean bounced = false; double dx =
     *         this.velocity.getDx(); double dy = this.velocity.getDy(); // If
     *         top or bottom is reached if (this.isOutX()) { dx *= -1; bounced =
     *         true; } // If left or right is reached if (this.isOutY()) { dy *=
     *         -1; bounced = true; } // If the velocity changed if (bounced) {
     *         this.velocity = new Velocity(dx, dy); } return bounced; }
     */
    /**
     * Moves the ball one step by its velocity. Performs collision check and
     * notifies the collided object as well.
     */
    public void moveOneStep() {
        // Compute the ball trajectory (with extra radius buffer):
        // (From the front point of the ball currently,
        // to the front point of the ball after the step)
        Line trajectory = new Line(Velocity.fromAngleAndSpeed(
                this.velocity.getAngle(), this.radius)
                .applyToPoint(this.center), Velocity.fromAngleAndSpeed(
                this.velocity.getAngle(),
                this.velocity.getSpeed() + this.radius).applyToPoint(
                this.center));
        CollisionInfo colInfo = this.environment.getClosestCollison(trajectory);
        // If a collision will happen
        if (colInfo != null) {
            // Move the ball to "almost" the hit point,
            // but just slightly before it.
            Point almostHit = getTouchingPoint(colInfo.collisionPoint());
            this.center = almostHit;
            // that a collision occurred.
            Velocity updated = colInfo.collisionObject().hit(this,
                    colInfo.collisionPoint(), this.velocity);
            // Check if the new velocity is equal to the previous.
            // If yes, move it one step!
            if (this.velocity.equals(updated)) {
                this.center = this.velocity.applyToPoint(this.center);
            }
            // Update the velocity to the new velocity
            // returned by the hit() method.
            this.velocity = updated;
        } else {
            // If no, then move the ball to the end of the trajectory
            this.center = this.getVelocity().applyToPoint(this.center);
        }
        // }
    }

    /**
     * Get the touching point for the collision.
     * @param collisionPoint the point of the collision
     * @return the touching point to serve as center to the ball
     */
    private Point getTouchingPoint(Point collisionPoint) {
        // Get the distance to do until the ball touches the collidable object
        double dist = this.center.distance(collisionPoint) - this.radius;
        double angle = this.velocity.getAngle();
        // If negative distance, move backwards
        if (dist <= 0) {
            angle += 180;
        }
        // Retain the angle
        Velocity stepToMake = Velocity.fromAngleAndSpeed(angle, dist);
        // Go to the touching point.
        return stepToMake.applyToPoint(this.center);
    }

    /**
     * Draws the ball on the given DrawSurface.
     * @param surface the surface to draw onto
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.radius);
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * Notify the ball that time has passed.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Add the ball to a game.
     * @param g the game to add the ball to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        this.environment = g.getEnvironment();
    }

    /**
     * Removes the ball from the game.
     * @param g the game to remove the ball from
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
