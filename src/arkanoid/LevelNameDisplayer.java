package arkanoid;

import biuoop.DrawSurface;

import core.Sprite;

import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * A Sprite that displays the name of the current level.
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public class LevelNameDisplayer implements Sprite {
    private String levelName;
    private Rectangle border;
    private Color color;

    /**
     * Constructor.
     * @param levelName the name of the level
     * @param border the limits of the displayer
     * @param color the color to draw the displayer in
     */
    public LevelNameDisplayer(String levelName, Rectangle border, Color color) {
        this.levelName = levelName;
        this.border = border;
        this.color = color;
    }

    /**
     * Change the level name the displayer holds.
     * @param newLevelName the name of the new level
     */
    public void setLevelName(String newLevelName) {
        this.levelName = newLevelName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawOn(DrawSurface d) {
        Point upperLeft = border.getUpperLeft();
        Point lowerRight = border.getLowerRight();
        d.setColor(this.color);
        d.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(),
                (int) border.getWidth(), (int) border.getHeight());
        d.setColor(Color.BLACK);
        String text = "Level Name: " + this.levelName;
        d.drawText(
                (int) (upperLeft.getX() + lowerRight.getX()
                        - text.length()) / 2,
                (int) upperLeft.getY() + 14, text, 16);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void timePassed() {
        return;
    }

}
