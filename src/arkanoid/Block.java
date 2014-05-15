package arkanoid;

import biuoop.DrawSurface;

import core.Collidable;
import core.HitListener;
import core.HitNotifier;
import core.Sprite;
import core.Velocity;

import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public class Block implements Sprite, Collidable, HitNotifier {
    private Rectangle border;
    private Color color;
    private int hits;
    private List<HitListener> hitListeners;

    /**
     * Constructor.
     * @param upperLeft the upper left corner of the block
     * @param width the block's width
     * @param height the block's height
     * @param color the block's color
     * @param hits the beginning number of hits counted of the block
     */
    public Block(Point upperLeft, double width, double height, Color color,
            int hits) {
        this.border = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.hits = hits;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * @return the "collision shape" of the object.
     */
    public Rectangle getCollisionRectangle() {
        return this.border;
    }

    /**
     * Get the hits points of the Block.
     * @return the Block's hit points
     */
    public int getHitPoints() {
        return this.hits;
    }

    /**
     * Get the color of the block.
     * @return the color of the block
     */
    public Color getColor() {
        return this.color;
    }
    /**
     * Set the color of the block.
     * @param newColor the new color
     */
    public void setColor(Color newColor) {
        this.color = newColor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
            Velocity currentVelocity) {
        if (this.hits > 0) {
            this.hits--;
        }
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        // Elastic collision on top or bottom - keep x, flip y
        if (this.border.topLine().isInLimits(collisionPoint)
                || this.border.bottomLine().isInLimits(collisionPoint)) {
            dy *= -1;
        }
        // Elastic collision on left or right - keep y, flip x
        if (this.border.leftLine().isInLimits(collisionPoint)
                || this.border.rightLine().isInLimits(collisionPoint)) {
            dx *= -1;
        }
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawOn(DrawSurface d) {
        Point upperLeft = this.border.getUpperLeft();
        d.setColor(this.color);
        d.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(),
                (int) this.border.getWidth(), (int) this.border.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) upperLeft.getX(), (int) upperLeft.getY(),
                (int) this.border.getWidth(), (int) this.border.getHeight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void timePassed() {
        return;
    }

    /**
     * Add the block to a game.
     * @param game the game to add the block to
     */
    public void addToGame(GameLevel game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

    /**
     * Remove the block to a game.
     * @param game the game to remove the block from
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notify all HitListeners about a hit event.
     * @param hitter the hitting ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(
                this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
