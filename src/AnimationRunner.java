//ID: 316125855
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * AnimationRunner class.
 * running the animation from the animation interface.
 * @author  Avital Aviv
 * @version 1.0
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;
    private static final int FRAME_PER_SECOND = 1000 / 60;
    /**
     * constructor method.
     * @param gui gui animtaion screen.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = FRAME_PER_SECOND;
        this.sleeper = new Sleeper();
    }

    /**
     * run method.
     * ruuning a given animation.
     * @param animation the given animation.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
