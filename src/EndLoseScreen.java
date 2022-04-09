//ID: 316125855

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * EndLoseScreen class
 * show this animation when the player lose in the game.
 * @author  Avital Aviv
 * @version 1.0
 */
public class EndLoseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;

    /**
     * constructor method.
     * @param k the keyboard sensor of the gui.
     * @param score the counter that count the score to show on the screen.
     */
    public EndLoseScreen(KeyboardSensor k, Counter score) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "GAME OVER! YOUR SCORE IS: " + this.score.getValue(), 32);
        d.drawText(10, d.getHeight() / 2 + d.getHeight() / 4, "PRESS SPACE TO CONTINUE", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
