package levels;

import biuoop.DrawSurface;

import core.Sprite;

import java.awt.Color;

/**
 * A background class.
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public class Background implements Sprite {
    private int screenWidth;
    private int screenHeight;
    private Color backgroundColor;

    /**
     * Constructor.
     * @param width the width of the screen
     * @param height the height of the screen
     * @param color the color of the background
     */
    public Background(int width, int height, Color color) {
        this.screenWidth = width;
        this.screenHeight = height;
        this.backgroundColor = color;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(backgroundColor);
        d.fillRectangle(0, 0, this.screenWidth, this.screenHeight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void timePassed() {
        return;
    }

}
