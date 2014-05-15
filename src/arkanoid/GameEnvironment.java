package arkanoid;

import core.Collidable;
import core.CollisionInfo;

import geometry.Line;
import geometry.Point;

import java.util.LinkedList;
import java.util.List;

/**
 * Collection of collidables of a game.
 * @author Tomer Dacidor
 * @author Guy Blachar
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Constructor.
     */
    public GameEnvironment() {
        this.collidables = new LinkedList<Collidable>();
    }

    /**
     * Add the given collidable to the environment.
     * @param c a collidable object
     */
    public void addColidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Remove the given collidable to the environment.
     * @param c a collidable object
     */
    public void removeColidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end(). If this object
     * will not collide with any of the collidables in this collection, return
     * null. Else, return the information about the closest collision that is
     * going to occur.
     * @param trajectory the trajectory to check
     * @return return the information about the closest collision that is going
     *         to occur
     */
    public CollisionInfo getClosestCollison(Line trajectory) {
        double minDistance = -1;
        Point closestPoint = null;
        Collidable closestCollidable = null;
        // Go through each collidable
        List<Collidable> collidablesCopy = new LinkedList<Collidable>(
                this.collidables);
        for (Object element : collidablesCopy) {
            Collidable c = (Collidable) element;
            List<Point> collisions = (c.getCollisionRectangle())
                    .intersectionPoints(trajectory);
            // Go through every point of each collidable
            for (Object object : collisions) {
                Point p = (Point) object;
                double dist = p.distance(trajectory.start());
                // Check if closest so far
                if (dist < minDistance || minDistance == -1) {
                    // Set the new minimum and the according info
                    minDistance = dist;
                    closestPoint = p;
                    closestCollidable = c;
                }
            } // end of points for
        } // end of collidables for
        if (minDistance > -1 && null != closestPoint
                && null != closestCollidable) {
            return new CollisionInfo(closestPoint, closestCollidable);
        }
        return null;
    }
}
