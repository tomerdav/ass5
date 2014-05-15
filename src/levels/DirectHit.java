package levels;

import arkanoid.Block;

import biuoop.DrawSurface;

import core.Velocity;

import geometry.Point;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The first level.
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public class DirectHit extends BaseLevel {
    // The center of the block to hit
    private Point target = new Point(400, 200);

    /**
     * Constructor.
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     */
    public DirectHit(int screenWidth, int screenHeight) {
        this.setLevelName("Direct Hit");
        this.setBackground(new Background(screenWidth, screenHeight,
                Color.BLACK));
        this.setPaddle();
        this.createBallsVelocities();
        this.createBlocks();
    }

    /**
     * Set the paddle parameters.
     */
    private void setPaddle() {
        this.setPaddleWidth(120);
        this.setPaddleSpeed(15);
    }

    /**
     * Create the velocities of the balls.
     */
    private void createBallsVelocities() {
        List<Velocity> ballVelocitiy = new LinkedList<Velocity>();
        ballVelocitiy.add(Velocity.fromAngleAndSpeed(0, 10));
        this.setBallsVelocities(ballVelocitiy);
        this.setNumberOfBalls(1);
    }

    /**
     * Create the blocks of the game.
     */
    private void createBlocks() {
        List<Block> blocksInLevel = new LinkedList<Block>();
        int length = 30;
        blocksInLevel.add(new Block(new Point(target.getX() - length / 2,
                target.getY() - length / 2), length, length, Color.RED, 1));
        this.setBlocksInLevel(blocksInLevel);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void drawBackgroundShapes(DrawSurface d) {
        Color bullseyeColor = Color.BLUE;
        int numOfCircles = 3;
        int radius = 30;
        d.setColor(bullseyeColor);
        // Draw the bullseye circles
        for (int i = 1; i <= numOfCircles; i++) {
            d.drawCircle((int) target.getX(), (int) target.getY(), i * radius);
        }
        // Draw the cross
        int offset = 10;
        d.drawLine((int) (target.getX() - radius * numOfCircles - offset),
                (int) (target.getY()), (int) (target.getX() + radius
                        * numOfCircles + offset), (int) (target.getY()));
        d.drawLine((int) (target.getX()), (int) (target.getY() - radius
                * numOfCircles - offset), (int) (target.getX()),
                (int) (target.getY()) + radius * numOfCircles + offset);
    }

}
