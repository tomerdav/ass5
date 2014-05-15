package core;

import arkanoid.Ball;

import geometry.Point;
import geometry.Rectangle;

/**
 * @author Guy Blachar
 * @author Tomer Davidor
 * @version 1.0
 */
public interface Collidable {
    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given
     * velocity. The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param collisionPoint the point of collision
     * @param currentVelocity the current velocity of the hitting object
     * @param hitter the hitting ball
     * @return the new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
