//ID: 316125855
import biuoop.DrawSurface;
/**
 * Animation interface.
 * arrange all the class that is responsible for the animation.
 * @author  Avital Aviv
 * @version 1.0
 */
public interface Animation {

    /**
     * doOneFrame method.
     * runs one frame of a given animation.
     * @param d the drawsurface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * shouldStop method.
     * returns if an animation should stop or not.
     * @return boolean value.
     */
    boolean shouldStop();
}
