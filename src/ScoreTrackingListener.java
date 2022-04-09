//ID: 316125855

/**
 * ScoreTrackingListener class.
 * count the score of a player in the game.
 * @author  Avital Aviv
 * @version 1.0
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor method.
     * @param scoreCounter the score counter of the game.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
