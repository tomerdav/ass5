package levels;

import arkanoid.Block;

import biuoop.DrawSurface;

import core.Velocity;

import geometry.Point;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The second level.
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public class WideEasy extends BaseLevel {
    /**
     * Constructor.
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     */
    public WideEasy(int screenWidth, int screenHeight) {
        this.setLevelName("Wide Easy");
        this.setBackground(new Background(screenWidth, screenHeight,
                Color.WHITE));
        this.setPaddle();
        this.createBallsVelocities();
        this.createBlocks();
    }

    /**
     * Set the paddle speed and weight.
     */
    private void setPaddle() {
        this.setPaddleSpeed(5);
        this.setPaddleWidth(600);
    }

    /**
     * Create the balls of the level.
     */
    private void createBallsVelocities() {
        List<Velocity> ballsVelocities = new LinkedList<Velocity>();
        double speed = 30;
        for (int i = 0; i < 5; i++) {
            Velocity vel1 = Velocity.fromAngleAndSpeed(10 * (i + 1), speed);
            ballsVelocities.add(vel1);
            Velocity vel2 = Velocity.fromAngleAndSpeed(-10 * (i + 1), speed);
            ballsVelocities.add(vel2);
        }
        this.setBallsVelocities(ballsVelocities);
        this.setNumberOfBalls(ballsVelocities.size());
    }

    /**
     * Create the blocks of the level.
     */
    private void createBlocks() {
        List<Block> blocksInLevel = new LinkedList<Block>();
        Color[] blocksColors = {Color.RED, Color.ORANGE, Color.YELLOW,
                Color.GREEN, Color.BLUE, Color.PINK, Color.CYAN };
        for (int i = 0; i < 15; i++) {
            Color blockColor;
            if (i < 7) {
                blockColor = blocksColors[i / 2];
            } else {
                blockColor = blocksColors[(i - 1) / 2];
            }
            Point upperLeft = new Point(25 + 50 * i, 250);
            Block block = new Block(upperLeft, 50, 30, blockColor, 1);
            blocksInLevel.add(block);
        }
        this.setBlocksInLevel(blocksInLevel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawBackgroundShapes(DrawSurface d) {
        // draws the lines
        d.setColor(Color.ORANGE);
        for (int i = 0; i < 150; i++) {
            d.drawLine(150, 150, 5 * i, 250);
        }

        // draws the sun
        d.setColor(Color.ORANGE.brighter().brighter());
        d.fillCircle(150, 150, 70);
        d.setColor(Color.ORANGE);
        d.fillCircle(150, 150, 50);
        d.setColor(Color.YELLOW);
        d.fillCircle(150, 150, 30);
    }
}
