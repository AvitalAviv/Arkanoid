//ID: 316125855

/**
 * HitListener interface.
 * follow the hit occurrences in the game.
 * @author  Avital Aviv
 * @version 1.0
 */
public interface HitListener {

    /**
     * hitEvent method.
     * called whenever the beingHit object is hit.
     * @param beingHit the block is being hit.
     * @param hitter the ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
