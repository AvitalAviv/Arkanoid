//ID: 316125855
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimation class.
 * arrange all the animations that are has the key press for stopping the animation.
 * @author  Avital Aviv
 * @version 1.0
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private Animation animation;
    private String key;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * constructor method.
     * @param sensor of the keyboard.
     * @param key the string.
     * @param animation the animation that is being run.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        /* run the animation that is given */
        this.animation.doOneFrame(d);

        /* if the given keyboard is pressed */
        if (this.sensor.isPressed(this.key)) {

            /* if is pressed that is has to stop */
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
