//ID: 316125855

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * AnimationRunner class.
 * running the animation from the animation interface.
 * @author  Avital Aviv
 * @version 1.0
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private double timeNow;

    /**
     * constructor method.
     * @param numOfSeconds 3 second to count for this animation.
     * @param countFrom the counter from/
     * @param gameScreen the screen of the game with all the sprites.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = 3;
        this.numOfSeconds = 2;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.timeNow = System.currentTimeMillis();
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);

        /* check if counter is at zero */
        if (this.countFrom == 0) {
            this.stop = true;
        }

        /* setting the color and the written text */
        d.setColor(Color.RED.darker());
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, timerShow(), 50);
    }

    /**
     * timerShow method.
     * calculating the time to show new number.
     * @return the number to show on the screen as a string.
     */
    public String timerShow() {
        double timer = System.currentTimeMillis();
        if ((timer - timeNow) > (numOfSeconds / (this.countFrom + 1) * 1000)) {
            this.timeNow = System.currentTimeMillis();
            this.countFrom--;
        }

        /* returning the number to print on the screen as a string. */
        if (this.countFrom != 0) {
            return "" + this.countFrom + "";
        }
        return "";
    }

    @Override
    public boolean shouldStop() {
        return !(countFrom > 0);
    }
}
