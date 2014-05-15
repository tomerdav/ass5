package arkanoid;

import biuoop.DrawSurface;

import core.Sprite;

import java.util.LinkedList;
import java.util.List;

/**
 * A collection of sprites.
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public class SpriteCollection {
    private List<Sprite> collection;

    /**
     * Create a collection of sprites. An arrayList was chosen because the
     * insert should be fast.
     */
    public SpriteCollection() {
        this.collection = new LinkedList<Sprite>();
    }

    /**
     * Add a sprite to the screen.
     * @param s a sprite object
     */
    public void addSprite(Sprite s) {
        this.collection.add(this.collection.size(), s);
    }

    /**
     * Remove a sprite to the screen.
     * @param s a sprite object
     */
    public void removeSprite(Sprite s) {
        this.collection.remove(s);
    }

    /**
     * Notify all sprites that time has passed.
     */
    public void notifyAllTimePassed() {
        List<Sprite> collectionCopy = new LinkedList<Sprite>(this.collection);
        for (Object elem : collectionCopy) {
            ((Sprite) elem).timePassed();
        }
    }

    /**
     * Draw all sprites to the screen.
     * @param d the surface to draw on
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> collectionCopy = new LinkedList<Sprite>(this.collection);
        for (Object elem : collectionCopy) {
            ((Sprite) elem).drawOn(d);
        }
    }
}
