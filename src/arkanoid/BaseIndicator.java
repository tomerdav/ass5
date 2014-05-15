package arkanoid;

import biuoop.DrawSurface;

import core.Counter;
import core.Sprite;

import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * The base class of an indicator (with counter).
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public abstract class BaseIndicator implements Sprite {
    private Counter currentValue;
    private Rectangle border;
    private Color color;

    /**
     * Constructor.
     * @param c the counter of the indicator
     * @param border the limits of the indicator
     * @param color the color to draw the indicator in
     */
    public BaseIndicator(Counter c, Rectangle border, Color color) {
        this.currentValue = c;
        this.border = border;
        this.color = color;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawOn(DrawSurface d) {
        Point upperLeft = this.border.getUpperLeft();
        Point lowerRight = this.border.getLowerRight();
        d.setColor(this.color);
        d.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(),
                (int) this.border.getWidth(), (int) this.border.getHeight());
        String text = this.text() + this.currentValue.getValue();
        d.setColor(Color.BLACK);
        d.drawText(
                (int) (upperLeft.getX() + lowerRight.getX()
                        - text.length()) / 2,
                (int) upperLeft.getY() + 14, text, 16);
    }

    /**
     * The text that shold be displayed along with the counter's value.
     * @return the text that shold be displayed with the counter's value
     */
    protected abstract String text();

    /**
     * {@inheritDoc}
     */
    @Override
    public void timePassed() {
        return;
    }

    /**
     * Get the counter of the indicator.
     * @return the counter of the indicator
     */
    protected Counter getCounter() {
        return this.currentValue;
    }
}