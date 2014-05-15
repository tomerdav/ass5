package core;

/**
 * HitNotifier class.
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public interface HitNotifier {
    /**
     * Add a HitListener to hit events.
     * @param hl the HitListener to add
     */
    void addHitListener(HitListener hl);

    /**
     * Remove a HitListener to hit events.
     * @param hl the HitListener to remove
     */
    void removeHitListener(HitListener hl);
}
