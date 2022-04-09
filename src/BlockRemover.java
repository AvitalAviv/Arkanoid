//ID: 316125855

/**
 * BlockRemover class.
 * make the block removed from the game when the hit event occurs.
 * @author  Avital Aviv
 * @version 1.0
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;
    private Counter counter;
    private static final int ONE_POINT = 1;

    /**
     * constructor method.
     * @param gameLevel the game object.
     * @param counter no need this counter.
     */
    public BlockRemover(GameLevel gameLevel, Counter counter) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = this.gameLevel.getNumOfBlocks();
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(gameLevel);
        this.remainingBlocks.decrease(ONE_POINT);
    }
}
