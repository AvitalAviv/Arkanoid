//ID: 316125855

/**
 * HitNotifier interface.
 * notify when a hit happens in the game.
 * @author  Avital Aviv
 * @version 1.0
 */
public interface HitNotifier {

    /**
     * addHitListener method.
     * Add hl as a listener to hit events.
     * @param hl the hit listener.
     */
    void addHitListener(HitListener hl);

    /**
     * addHitListener method.
     * Remove hl from the list of listeners to hit events.
     * @param hl the hit listener.
     */
    void removeHitListener(HitListener hl);
}
