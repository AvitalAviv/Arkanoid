//ID: 316125855

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * EndWinScreen class
 * show this animation when the player win in the game.
 * @author  Avital Aviv
 * @version 1.0
 */
public class EndWinScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;

    /**
     * constructor method.
     * @param k the keyboard sensor of the gui.
     * @param score the counter that count the score to show on the screen.
     */
    public EndWinScreen(KeyboardSensor k, Counter score) {
        this.keyboard = k;
        this.score = score;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "YOU WIN! YOU SCORE IS: " + this.score.getValue(), 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
