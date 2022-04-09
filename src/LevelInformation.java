//ID: 316125855
import biuoop.DrawSurface;
import java.util.List;

/**
 * LevelInformation interface.
 * all the methods in the level classes.
 * @author  Avital Aviv
 * @version 1.0
 */
public interface LevelInformation {

    /**
     * numberOfBalls method.
     * @return number of balss in the game.
     */
    int numberOfBalls();

    /**
     * initialBallVelocities method.
     * The initial velocity of each ball.
     * @return list of velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * paddleSpeed method.
     * @return the paddle speed.
     */
    int paddleSpeed();

    /**
     * paddleWidth method.
     * @return the paddle width.
     */
    int paddleWidth();

    /**
     * levelName method.
     * the level name will be displayed at the top of the screen.
     * @return the paddle width.
     */
    String levelName();

    /**
     * getBackground method.
     * Returns a sprite with the background of the level
     * @return the sprite blocks that appears in the background of the game.
     */
    Sprite getBackground();

    /**
     * blocks method.
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return list of blocks.
     */
    List<Block> blocks();

    /**
     * numberOfBlocksToRemove method.
     * Number of blocks that should be removed before the level is considered to be "cleared".
     * @return number of the blocks.
     */
    int numberOfBlocksToRemove();

    /**
     * getScreenAnimation method.
     * creating the object that all the screen animation appears in.
     * @param d the drawsurface.
     * @return sprite of the screen.
     */
    Sprite getScreenAnimation(DrawSurface d);
}
