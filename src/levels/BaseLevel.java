package levels;

import arkanoid.Block;

import biuoop.DrawSurface;

import core.Sprite;
import core.Velocity;

import java.util.ArrayList;
import java.util.List;

/**
 * The basic level class.
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public abstract class BaseLevel implements LevelInformation {
    private String levelName;
    private Sprite background;
    private int paddleSpeed;
    private int paddleWidth;
    private int numberOfBalls;
    private List<Velocity> ballsVelocities;
    private List<Block> blocksInLevel;

    /**
     * {@inheritDoc}
     */
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return new ArrayList<Velocity>(this.ballsVelocities);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String levelName() {
        return this.levelName + "";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Block> blocks() {
        return new ArrayList<Block>(this.blocksInLevel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int numberOfBlocksToRemove() {
        int numToRemove = 0;
        for (Block block : this.blocksInLevel) {
            if (block.getHitPoints() > 0) {
                numToRemove++;
            }
        }
        return numToRemove;
    }

    /**
     * @return the levelName
     */
    protected String getLevelName() {
        return levelName;
    }

    /**
     * @param name the levelName to set
     */
    protected void setLevelName(String name) {
        this.levelName = name;
    }

    /**
     * @return the paddleSpeed
     */
    protected int getPaddleSpeed() {
        return paddleSpeed;
    }

    /**
     * @param speed the paddleSpeed to set
     */
    protected void setPaddleSpeed(int speed) {
        this.paddleSpeed = speed;
    }

    /**
     * @return the paddleWidth
     */
    protected int getPaddleWidth() {
        return paddleWidth;
    }

    /**
     * @param width the paddleWidth to set
     */
    protected void setPaddleWidth(int width) {
        this.paddleWidth = width;
    }

    /**
     * @return the numberOfBalls
     */
    protected int getNumberOfBalls() {
        return numberOfBalls;
    }

    /**
     * @param numberOfBallsInLevel the numberOfBalls to set
     */
    protected void setNumberOfBalls(int numberOfBallsInLevel) {
        this.numberOfBalls = numberOfBallsInLevel;
    }

    /**
     * @return the ballsVelocities
     */
    protected List<Velocity> getBallsVelocities() {
        return ballsVelocities;
    }

    /**
     * @param newBallsVelocities the ballsVelocities to set
     */
    protected void setBallsVelocities(List<Velocity> newBallsVelocities) {
        this.ballsVelocities = newBallsVelocities;
    }

    /**
     * @return the blocksInLevel
     */
    protected List<Block> getBlocksInLevel() {
        return blocksInLevel;
    }

    /**
     * @param blocks the blocksInLevel to set
     */
    protected void setBlocksInLevel(List<Block> blocks) {
        this.blocksInLevel = blocks;
    }

    /**
     * @param newBackground the background to set
     */
    protected void setBackground(Sprite newBackground) {
        this.background = newBackground;
    }

    /**
     * Draw the background shapes in the level.
     * @param d the surface to draw on
     */
    protected abstract void drawBackgroundShapes(DrawSurface d);

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawBackground(DrawSurface d) {
        this.background.drawOn(d);
        this.drawBackgroundShapes(d);
    }
}
