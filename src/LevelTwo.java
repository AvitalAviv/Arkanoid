//ID: 316125855
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * LevelTwo class.
 * all the methods in the level classes.
 * @author  Avital Aviv
 * @version 1.0
 */
public class LevelTwo implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {

        /* creating the list of the velocities and initialize them as needed */
        List<Velocity> listVelo = new ArrayList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            Velocity velocity = Velocity.fromAngleAndSpeed(315 + i * 10, 4);
            listVelo.add(velocity);
        }

        /* returning the list of the velocities */
        return listVelo;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 400;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        Block backGround = new Block(new Rectangle(new Point(10, 30), 780, 590), Color.WHITE);
        return backGround;
    }

    @Override
    public List<Block> blocks() {
        List<Block> listBlocks = new ArrayList<>();
        for (int i = 0; i < numberOfBlocksToRemove(); i++) {
            Block b = new Block(new Rectangle(new Point(10 + 52 * i, 200), 52, 20), choseColor(i));
            listBlocks.add(b);
        }
        return listBlocks;
    }

    /**
     * choseColor method.
     * @param i the index in the loop.
     * @return color to the block.
     */
    public Color choseColor(int i) {
        if (i == 0 || i == 1) {
            return Color.red;
        }
        if (i == 2 || i == 3) {
            return Color.orange;
        }
        if (i == 4 || i == 5) {
            return Color.yellow;
        }
        if (i == 6 || i == 7 || i == 8) {
            return Color.green;
        }
        if (i == 9 || i == 10) {
            return Color.blue;
        }
        if (i == 11 || i == 12) {
            return Color.pink;
        }
        if (i == 13 || i == 14) {
            return Color.CYAN;
        }
        return null;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }

    @Override
    public Sprite getScreenAnimation(DrawSurface d) {
        return new ScreenLevelTwo(d);
    }
}
