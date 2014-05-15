package arkanoid;

import core.HitListener;

/**
 * A HitListener that changes the color of a block when it is hit.
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public class ColorChanger implements HitListener {
    /**
     * {@inheritDoc}
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.setColor(beingHit.getColor().darker());
    }
}
