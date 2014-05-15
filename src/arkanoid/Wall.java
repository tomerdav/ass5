package arkanoid;

import biuoop.DrawSurface;

import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * A class that represents a wall in the game.
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public class Wall extends Block {
    /**
     * Constructor.
     * @param upperLeft the upper left corner of the wall
     * @param width the wall's width
     * @param height the wall's height
     * @param color the wall's color
     */
    public Wall(Point upperLeft, double width, double height, Color color) {
        super(upperLeft, width, height, color, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        d.setColor(this.getColor());
        Rectangle border = this.getCollisionRectangle();
        Point upperLeft = border.getUpperLeft();
        d.drawRectangle((int) upperLeft.getX(), (int) upperLeft.getY(),
                (int) border.getWidth(), (int) border.getHeight());
    }
}
