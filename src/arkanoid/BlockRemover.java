package arkanoid;

import core.Counter;
import core.HitListener;

/**
 * A BlockRemover is in charge of removing blocks from the game, as well as
 * keeping count of the number of blocks that were removed.
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter removedBlocks;

    /**
     * Constructor.
     * @param game the game of the BlockRemover
     * @param removedBlocks a Counter of the removed blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.removedBlocks = removedBlocks;
    }

    /**
     * Blocks that are hit and reach 0 hit-points are removed from the game.
     * @param beingHit the Block to check
     * @param hitter the hitting Ball
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeFromGame(this.game);
            beingHit.removeHitListener(this);
            this.removedBlocks.increase(1);
        }
    }
}
