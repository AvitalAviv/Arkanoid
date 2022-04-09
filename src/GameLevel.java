//ID: 316125855

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Game class.
 * the class that creates all the objects in the game, initialize them and running the animation.
 * @author  Avital Aviv
 * @version 1.0
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Block deathBlock;
    private java.util.ArrayList<Ball> listBalls;
    private java.util.ArrayList<Block> listOfBlocks;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private boolean running;
    private LevelInformation levelInformation;
    private Counter blockCount;
    private Counter ballCount;
    private Counter gameCount;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int FIRST_ROW = 0;
    private static final int SECOND_ROW = 1;
    private static final int THIRD_ROW = 2;
    private static final int FOURTH_ROW = 3;
    private static final int FIFTH_ROW = 4;
    private static final int SIXTH_ROW = 5;
    private static final int SIZE_BALL = 5;
    private static final int WIDTH_BORDER = 10;
    private static final int BONUS_POINTS = 100;

    /**
     * Game constructor.
     * creating the array list of sprites, balls and game environment, creates the gui.
     * @param levelInformation the level that is has to run.
     * @param ar the animation runner.
     * @param keyboard the keyboard sensor.
     * @param gui the gui animation.
     * @param gameCount the counter of the points in the game.
     */
    public GameLevel(LevelInformation levelInformation, AnimationRunner ar, KeyboardSensor keyboard, GUI gui,
                     Counter gameCount) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = gui;
        this.listBalls = new ArrayList<Ball>();
        this.listOfBlocks = new ArrayList<Block>();
        this.runner = new AnimationRunner(gui);
        this.keyboard = this.gui.getKeyboardSensor();
        this.levelInformation = levelInformation;
        this.runner = ar;
        this.gameCount = gameCount;
    }

    /**
     * addCollidable method.
     * adding an collidable object to the list of collidables.
     * @param c collidable object.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * addSprite method.
     * adding sprite to the game.
     * @param s sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * getGUI method.
     * getter method to get the gui object.
     * @return gui anumation.
     */
    public GUI getGUI() {
        return gui;
    }

    /**
     * getListBalls method.
     * getter method to get the array of the balls saved in the game.
     * @return arraylist of balls.
     */
    public java.util.ArrayList getListBalls() {
        return this.listBalls;
    }

    /**
     * createBorders method.
     * creating the borders in the animation, by creating the blocks in the borders is given.
     */
    public void createBorders() {

        /* initialize the list of the blocks */
        java.util.ArrayList<Block> listBlocks = new ArrayList<>();

        /* creating the blocks and add them to the list */
        listBlocks.add(new Block(new Rectangle(new Point(0, 20), WIDTH, WIDTH_BORDER), Color.GRAY));
        listBlocks.add(new Block(new Rectangle(new Point(0, 20), WIDTH_BORDER, HEIGHT), Color.GRAY));
        listBlocks.add(new Block(new Rectangle(new Point(WIDTH - WIDTH_BORDER, 20), WIDTH_BORDER, HEIGHT),
                Color.GRAY));

        /* add the blocks to the game by the addToGame method */
        for (Block b : listBlocks) {
            b.addToGame(this);
        }
    }

    /**
     * getNumOfBlocks method.
     * @return counter of blocks
     */
    public Counter getNumOfBlocks() {
        return blockCount;
    }

    /**
     * getNumOfBalls method.
     * @return counter of balls in the game.
     */
    public Counter getNumOfBalls() {
        return ballCount;
    }

    /**
     * initialize method.
     * calls all the method to initialie all the objects in the game.
     */
    public void initialize() {

        /* creating the borders in the game */
        createBorders();

        /* the background block */
        Block backGround = (Block) levelInformation.getBackground();
        backGround.addToGame(this);

        /* the animation behind the game */
        this.sprites.addSprite(this.levelInformation.getScreenAnimation(this.gui.getDrawSurface()));

        /* initialize the balls */
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            Ball b = new Ball(new Point(400, 550), 5, Color.WHITE, this.environment);
            b.setVelocity(levelInformation.initialBallVelocities().get(i).getDx(),
                    levelInformation.initialBallVelocities().get(i).getDy());
            b.addToGame(this);
        }

        /* counters */
        this.ballCount = new Counter(0);
        ballCount.increase(levelInformation.numberOfBalls());
        this.blockCount = new Counter(0);
        blockCount.increase(levelInformation.numberOfBlocksToRemove());
        BlockRemover blockRemover = new BlockRemover(this, null);
        ScoreIndicator scoreIndicator = new ScoreIndicator(gameCount, this.levelInformation);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(gameCount);
        scoreIndicator.addToGame(this);

        /* creating the blocks */
        for (Block block : this.levelInformation.blocks()) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
        }

        /* initialize the paddle */
        Paddle p = new Paddle(this, new Point(350, 580), levelInformation.paddleWidth(), 15,
                Color.YELLOW.darker(), levelInformation.paddleSpeed());
        p.addToGame(this);

        /* death block that make the game exit when all the balls disappear from the screen */
        deathBlock = new Block(new Rectangle(new Point(0, HEIGHT + WIDTH_BORDER), WIDTH, WIDTH_BORDER),
                Color.BLACK);
        deathBlock.addToGame(this);

        /* creating a ballremover object and make the death block as a hit listener */
        BallRemover ballRemover = new BallRemover(this, ballCount);
        deathBlock.addHitListener(ballRemover);

        /* countdown before level starts */
        CountdownAnimation countdownAnimation = new CountdownAnimation(3, 2, this.sprites);
        this.runner.run(countdownAnimation);
    }

    /**
     * removeCollidable method.
     * removing the collidable from the list.
     * @param c - the collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * removeSprite method.
     * removing the sprite from the list.
     * @param s - the sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        Sleeper sleeper = new Sleeper();
        while (!shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            d = gui.getDrawSurface();
            d.setColor(Color.BLACK);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (this.blockCount.getValue() == 0) {
                this.gameCount.increase(BONUS_POINTS);
                this.running = false;
            }
            if (this.ballCount.getValue() == 0) {
                this.runner.run(new EndLoseScreen(this.keyboard, gameCount));
                this.running = false;
                this.gui.close();
            }

            /* if user paused the game running the pause screen animation */
            if (this.keyboard.isPressed("p") || this.keyboard.isPressed("P"))  {
                this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                        new PauseScreen(this.keyboard)));
            }
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * run method.
     * ruuning the animation of all the things we created before.
     */
    public void run() {
        //this.createBallsOnTopOfPaddle();
        //this.runner.run(new CountdownAnimation(this.sprites));
        this.running = true;
        this.runner.run(this);
    }
}
