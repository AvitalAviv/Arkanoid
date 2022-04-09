//ID: 316125855
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * LevelOne class.
 * all the methods in the level classes.
 * @author  Avital Aviv
 * @version 1.0
 */
public class LevelOne implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {

        /* creating the list of the velocities and initialize them as needed */
        List<Velocity> listVelo = new ArrayList<>();
        Velocity v = new Velocity(0, -3);
        listVelo.add(v);

        /* returning the list of the velocities */
        return listVelo;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        Block backGround = new Block(new Rectangle(new Point(10, 30), 780, 590), Color.BLACK);
        return backGround;
    }

    @Override
    public Sprite getScreenAnimation(DrawSurface d) {
        return new ScreenLevelOne(d);
    }

    @Override
    public List<Block> blocks() {
        List<Block> listBlocks = new ArrayList<>();
        Block b = new Block(new Rectangle(new Point(375, 200), 50, 50), Color.red);
        listBlocks.add(b);
        return listBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
