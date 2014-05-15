package arkanoid;

import core.Counter;
import core.HitListener;

/**
 * A HitListener that removes balls when needed.
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter ballsCounter;

    /**
     * Constructor.
     * @param game The game reference.
     * @param ballsCounter The balls counter reference.
     */
    public BallRemover(GameLevel game, Counter ballsCounter) {
        this.game = game;
        this.ballsCounter = ballsCounter;
    }

    /**
     * Balls that hit the death region are removed from the game.
     * @param beingHit ignored
     * @param hitter the hitting Ball
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        this.ballsCounter.increase(1);
    }

}
