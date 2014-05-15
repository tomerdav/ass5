package arkanoid;

import core.Counter;

import geometry.Rectangle;

import java.awt.Color;

/**
 * A Sprite that follows the score of the game.
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public class ScoreIndicator extends BaseIndicator {
    /**
     * Constructor.
     * @param scoreCounter the counter of the score
     * @param border the limits of the indicator
     * @param color the color to draw the indicator in
     */
    public ScoreIndicator(Counter scoreCounter, Rectangle border, Color color) {
        super(scoreCounter, border, color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String text() {
        return "Score: ";
    }
}
