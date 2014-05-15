package arkanoid;

import core.Counter;
import core.HitListener;

/**
 * A HitListener that keeps track of the score.
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor.
     * @param scoreCounter the counter of the score in a game
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            this.currentScore.increase(15);
        } else {
            this.currentScore.increase(5);
        }
    }
}
