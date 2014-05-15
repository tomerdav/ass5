package levels;

import arkanoid.Block;

import biuoop.DrawSurface;

import core.Sprite;
import core.Velocity;

import java.util.List;

/**
 * Information about a level interface.
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public interface LevelInformation {
    /**
     * The number of balls in the level.
     * @return the number of balls in the level
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * @return a list of the initial velocities of each ball
     */
    List<Velocity> initialBallVelocities();

    /**
     * The speed of the paddle.
     * @return the velocity of the paddle
     */
    int paddleSpeed();

    /**
     * The paddle's width.
     * @return the width of the paddle
     */
    int paddleWidth();

    /**
     * The level's name.
     * @return the name of the level
     */
    String levelName();

    /**
     * A sprite of the background.
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color
     * and location.
     * @return a list of the blocks in the level
     */
    List<Block> blocks();

    /**
     * The number of blocks that should be removed, before this level is
     * considered "clear".
     * @return the number of blocks that should be removed
     */
    int numberOfBlocksToRemove();

    /**
     * Draw the background of the level.
     * @param d the surface to draw on
     */
    void drawBackground(DrawSurface d);
}
