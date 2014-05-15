package core;

import arkanoid.Ball;
import arkanoid.Block;

/**
 * HitListener interface.
 * @author User
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit. The hitter
     * parameter is the Ball that's doing the hitting.
     * @param beingHit the Block that is hit
     * @param hitter the Ball that hits the object
     */
    void hitEvent(Block beingHit, Ball hitter);
}
