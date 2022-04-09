//ID: 316125855
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * LevelThree class.
 * all the methods in the level classes.
 * @author  Avital Aviv
 * @version 1.0
 */
public class LevelThree implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {

        /* creating the list of the velocities and initialize them as needed */
        List<Velocity> listVelo = new ArrayList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            Velocity velocity = Velocity.fromAngleAndSpeed(330 + i * 60, 4);
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
        return 200;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        Block backGround = new Block(new Rectangle(new Point(10, 30), 780, 590),
                new Color(102, 255, 102).darker().darker());
        return backGround;
    }

    @Override
    public List<Block> blocks() {
        List<Block> listOfBlocks = new ArrayList<>();
        int elementsInRow;
        int yValue;
        int xValue;

        /* running through all the rows */
        for (int i = 0; i < 5; i++) {

            /* calculating the elements in a row according to the row */
            elementsInRow = 10 - i;

            /* calculating the x,y value of every block according to the row */
            yValue = (i * 20) + 150;
            xValue = 290 + (i * 50);
            for (int j = 0; j < elementsInRow; j++) {

                if (j != 0) {
                    /* creating the blocks */
                    listOfBlocks.add(new Block(new Rectangle(new Point(xValue + 50, yValue),
                            50, 20), createColor(i)));
                    xValue = xValue + 50;
                }
            }
        }
        return listOfBlocks;
    }

    /**
     * choseColor method.
     * @param loop the index in the loop.
     * @return color to the block.
     */
    private Color createColor(int loop) {
        if (loop == 0) {
            return Color.GRAY;
        }
        if (loop == 1) {
            return Color.red;
        }
        if (loop == 2) {
            return Color.YELLOW;
        }
        if (loop == 3) {
            return Color.BLUE;
        }
        if (loop == 4) {
            return Color.WHITE;
        }
        return null;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 35;
    }

    @Override
    public Sprite getScreenAnimation(DrawSurface d) {
        return new ScreenLevelThree(d);
    }
}
