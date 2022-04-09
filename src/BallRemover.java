//ID: 316125855

/**
 * BallRemover class.
 * make the ball removed from the game when hit the death block under all blocks.
 * @author  Avital Aviv
 * @version 1.0
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;
    private static final int ONE_POINT = 1;

    /**
     * constructor method.
     * @param gameLevel the game object.
     * @param remainingBalls the num of balls remain the the game.
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = this.gameLevel.getNumOfBalls();

    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        remainingBalls.decrease(ONE_POINT);
    }
}
