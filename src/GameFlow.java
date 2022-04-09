//ID: 316125855

import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.List;

/**
 * GameFlow class.
 * controls the game flow - winning and loosing in the game.
 * @author  Avital Aviv
 * @version 1.0
 */
public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private GUI gui;
    private Counter gameScore = new Counter(0);
    private GameEnvironment gameEnvironment;

    /**
     * constructor method.
     * @param ar the animation runner.
     * @param ks the keyboard sensor of the gui.
     * @param gui the gui animator.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.ar = ar;
        this.ks = ks;
        this.gui = gui;
    }

    /**
     * runLevels method.
     * @param levels the levels that it has to run.
     */
    public void runLevels(List<LevelInformation> levels) {

        /* going over the levels in the given list*/
        for (LevelInformation levelInfo : levels) {

            /* creating the gamelevel object */
            GameLevel level = new GameLevel(levelInfo, this.ar, this.ks, this.gui, gameScore);
            level.initialize();
            while (level.getNumOfBlocks().getValue() != 0 && level.getNumOfBalls().getValue() != 0) {
                level.run();
                if (this.ks.isPressed("p") || this.ks.isPressed("P"))  {
                    this.ar.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY,
                            new PauseScreen(this.ks)));
                }
            }
            if (level.getNumOfBalls().getValue() == 0) {
                this.ar.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY,
                        new EndLoseScreen(ks, gameScore)));
                gui.close();
                return;
            }
            }
        this.ar.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY, new EndWinScreen(ks, gameScore)));
        gui.close();
        return;
        }
}
