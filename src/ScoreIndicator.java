//ID: 316125855

import java.awt.Color;
import biuoop.DrawSurface;

/**
 * ScoreIndicator interface.
 * implements in Sprite collection.
 * @author  Avital Aviv
 * @version 1.0
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private LevelInformation levelInformation;
    private static final int WIDTH_SCREEN = 800;
    private static final int HEIGHT_SCREEN = 20;
    private static final int START_POINT = 0;
    private static final int WRITING_SIZE = 15;
    private static final int X_VALUE_WRITE = 360;
    private static final int Y_VALUE_WRITE = 17;

    /**
     * constructor method.
     * @param counter the counter.
     * @param levelInformation the level that is given.
     */
    public ScoreIndicator(Counter counter, LevelInformation levelInformation) {
        this.score = counter;
        this.levelInformation = levelInformation;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(START_POINT, START_POINT, WIDTH_SCREEN, HEIGHT_SCREEN);
        d.setColor(Color.BLACK);
        d.drawText(X_VALUE_WRITE, Y_VALUE_WRITE, "Score: " + this.score.getValue(), WRITING_SIZE);
        d.drawText(100, Y_VALUE_WRITE, "Live: 7", WRITING_SIZE);
        d.drawText(550, Y_VALUE_WRITE, "Level Name: " + this.levelInformation.levelName(), WRITING_SIZE);
    }

    @Override
    public void timePassed() {
    }

    /**
     * addToGame method.
     * adding the ball to the game.
     * @param gameLevel prameter.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
