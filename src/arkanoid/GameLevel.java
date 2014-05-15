package arkanoid;

import animation.Animation;
import animation.AnimationRunner;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import core.Collidable;
import core.Counter;
import core.HitListener;
import core.Sprite;
import core.Velocity;

import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.util.List;

import levels.LevelInformation;

/**
 * GameLevel class.
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public class GameLevel implements Animation {
    private LevelInformation level;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private KeyboardSensor keyboardSensor;
    private Paddle paddle;
    private Counter removedBlocks;
    private Counter removedBalls;
    private Counter livesCounter;
    private Counter scoreCounter;
    private int numberOfBlocksAtFirst;
    private int numberOfBallsAtFirst;
    private Indicator indicator;
    private boolean running;
    private AnimationRunner runner;
    private int surfaceWidth;
    private int surfaceHeight;
    private static final int WALL_WIDTH = 25;

    /**
     * Constructor.
     * @param level the level
     * @param ks a KeyBoardSensor for the Paddle
     * @param ar the AnimationRunner which runs the level
     * @param score a counter of the score in the entire game
     * @param currentLives a counter of the lives in the entire game
     * @param surfaceWidth the width of the GUI
     * @param surfaceHeight the height of the GUI
     */
    public GameLevel(LevelInformation level, KeyboardSensor ks,
            AnimationRunner ar, Counter score, Counter currentLives,
            int surfaceWidth, int surfaceHeight) {
        this.level = level;
        this.keyboardSensor = ks;
        this.runner = ar;
        this.scoreCounter = score;
        this.livesCounter = currentLives;
        this.surfaceWidth = surfaceWidth;
        this.surfaceHeight = surfaceHeight;
    }

    /**
     * Add a collidable object to the game.
     * @param c a collidable to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addColidable(c);
    }

    /**
     * Remove a collidable object to the game.
     * @param c a collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeColidable(c);
    }

    /**
     * Add a sprite object to the game.
     * @param s a sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Remove a sprite object to the game.
     * @param s a sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * @return the GameEnvironment instance
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add
     * them to the game.
     */
    public void initialize() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.removedBlocks = new Counter(0);
        this.numberOfBallsAtFirst = this.level.numberOfBalls();
        this.createWalls();
        this.createBlocks();
        this.createIndicators();
        this.createPaddle();
        this.createDeathRegion();
        this.running = true;
    }

    /**
     * create the indicators of the level.
     */
    private void createIndicators() {
        // Lives
        this.removedBalls = new Counter(0);
        // Level name
        Rectangle dispBorder = new Rectangle(new Point(350, 0), 450, 20);
        LevelNameDisplayer disp = new LevelNameDisplayer(
                this.level.levelName(), dispBorder, Color.LIGHT_GRAY);
        // The indicator sprite itself
        this.indicator = new Indicator(disp);
        this.addSprite(this.indicator);
        // Lives
        Rectangle livesBorder = new Rectangle(new Point(0, 0), 350, 20);
        LivesIndicator livesIndicator = new LivesIndicator(this.livesCounter,
                livesBorder, Color.LIGHT_GRAY);
        this.indicator.addIndicator(livesIndicator);
        // Score
        Rectangle scoreBorder = new Rectangle(new Point(300, 0), 150, 20);
        ScoreIndicator scoreInd = new ScoreIndicator(this.scoreCounter,
                scoreBorder, Color.LIGHT_GRAY);
        this.indicator.addIndicator(scoreInd);
    }

    /**
     * Create the two balls in the game.
     */
    private void createBalls() {
        List<Velocity> velocities = this.level.initialBallVelocities();
        for (int i = 0; i < this.level.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point(this.surfaceWidth / 2,
                    this.surfaceHeight - 50), 5, Color.WHITE,
                    this.surfaceWidth, this.surfaceHeight);
            ball.setVelocity(velocities.get(i));
            ball.addToGame(this);
        }
        // Reseting the counter for a new turn
        this.removedBalls.decrease(this.removedBalls.getValue());
    }

    /**
     * Create the two walls in the game.
     */
    private void createWalls() {
        // Blocks of the limits of the board
        Block up = new Wall(new Point(0, 20), this.surfaceWidth,
                GameLevel.WALL_WIDTH, Color.GRAY);
        up.addToGame(this);
        Block right = new Wall(new Point(this.surfaceWidth - 25, 45),
                GameLevel.WALL_WIDTH, this.surfaceHeight - 40, Color.GRAY);
        right.addToGame(this);
        Block left = new Wall(new Point(0, 45), GameLevel.WALL_WIDTH,
                this.surfaceHeight - 40, Color.GRAY);
        left.addToGame(this);
    }

    /**
     * Create the blocks in the game.
     */
    private void createBlocks() {
        List<Block> blocks = this.level.blocks();
        HitListener blockRemover = new BlockRemover(this, this.removedBlocks);
        HitListener scoreTracker = new ScoreTrackingListener(this.scoreCounter);
        HitListener colorChanger = new ColorChanger();
        for (Block block : blocks) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTracker);
            block.addHitListener(colorChanger);
        }
        this.numberOfBlocksAtFirst = this.level.numberOfBlocksToRemove();
    }

    /**
     * Create the paddle in the game.
     */
    private void createPaddle() {
        int paddleWidth = this.level.paddleWidth();
        Rectangle paddleBorder =
                new Rectangle(new Point((this.surfaceWidth - paddleWidth) / 2,
                560), paddleWidth, 20);
        Paddle paddle1 = new Paddle(this.keyboardSensor, paddleBorder,
                Color.ORANGE, this.level.paddleSpeed());
        paddle1.setLimits(25, this.surfaceWidth - 25);
        paddle1.addToGame(this);
        this.paddle = paddle1;
    }

    /**
     * Create the death region.
     */
    private void createDeathRegion() {
        HitListener hl = new BallRemover(this, this.removedBalls);
        Block death = new Block(new Point(0, this.surfaceHeight),
                this.surfaceWidth, 20, Color.GRAY, 0);
        death.addToGame(this);
        death.addHitListener(hl);
    }

    /**
     * Center the paddle at the beginning of each turn.
     */
    private void centerPaddle() {
        int paddleWidth = this.level.paddleWidth();
        this.paddle.movePaddleTo(
                new Point((this.surfaceWidth - paddleWidth) / 2,
                        this.surfaceHeight - 40));
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void playOneTurn() {
        // Beginning of a turn
        this.createBalls();
        this.centerPaddle();
        // Countdown before turn starts.
        CountdownAnimation countDown = new CountdownAnimation(2.0, 3,
                this.sprites, this.level);
        this.runner.run(countDown);
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * Draws the frame.
     * @param d a DrawSurface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.level.drawBackground(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        // no more blocks
        if (this.numberOfBlocksAtFirst == this.removedBlocks.getValue()) {
            this.scoreCounter.increase(100);
            this.running = false;
        }
        // no more balls
        if (this.numberOfBallsAtFirst == this.removedBalls.getValue()) {
            this.running = false;
        }
        // Pause
        if (this.keyboardSensor.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboardSensor));
        }
    }

    /**
     * @return whether the game should stop.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
