package core;

import biuoop.DrawSurface;

/**
 * A sprite interface.
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public interface Sprite {
    /**
     * Draw the sprite to the screen.
     * @param d the surface to draw on
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();
}