package animation;

import biuoop.DrawSurface;

/**
 * Interface for animation classes.
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public interface Animation {
    /**
     * Handles the GameLevel-specific logic.
     * @param d the DrawSurface to draw on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Checks the stopping conditions for the game.
     * @return true if the game should top. false if should run.
     */
    boolean shouldStop();
}
