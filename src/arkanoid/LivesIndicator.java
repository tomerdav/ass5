package arkanoid;

import core.Counter;

import geometry.Rectangle;

import java.awt.Color;

/**
 * A sprite that follows the lives of the user.
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public class LivesIndicator extends BaseIndicator {
    /**
     * Constructor.
     * @param livesCounter the Counter of lives in the game
     * @param border the border of the sprite
     * @param color the color of the sprite
     */
    public LivesIndicator(Counter livesCounter, Rectangle border, Color color) {
        super(livesCounter, border, color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String text() {
        return "Lives: ";
    }
}
