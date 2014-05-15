package levels;

import arkanoid.Ball;
import arkanoid.Block;

import biuoop.DrawSurface;

import core.Velocity;

import geometry.Point;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The fourth level.
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public class FinalFour extends BaseLevel {
    private int firstCloudLeftX; // animation

    /**
     * Constructor.
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     */
    public FinalFour(int screenWidth, int screenHeight) {
        this.setLevelName("Final Four");
        this.setBackground(new Background(screenWidth, screenHeight,
                new Color(0, 191, 255)));
        this.setPaddle();
        this.createBallsVelocities();
        this.createBlocks();
        this.firstCloudLeftX = 100;
    }

    /**
     * Set the paddle speed and weight.
     */
    private void setPaddle() {
        this.setPaddleSpeed(15);
        this.setPaddleWidth(120);
    }

    /**
     * Create the balls of the level.
     */
    private void createBallsVelocities() {
        List<Velocity> ballsVelocities = new LinkedList<Velocity>();
        ballsVelocities.add(Velocity.fromAngleAndSpeed(330, 10));
        ballsVelocities.add(Velocity.fromAngleAndSpeed(0, 10));
        ballsVelocities.add(Velocity.fromAngleAndSpeed(30, 10));
        this.setBallsVelocities(ballsVelocities);
        this.setNumberOfBalls(ballsVelocities.size());
    }

    /**
     * Create the blocks of the level.
     */
    private void createBlocks() {
        List<Block> blocksInLevel = new LinkedList<Block>();
        Color[] blocksColors = {Color.GRAY, Color.RED, Color.YELLOW,
                Color.GREEN, Color.WHITE, Color.PINK, Color.CYAN };
        for (int i = 0; i < blocksColors.length; i++) {
            int hitPoints = 1;
            if (i == 0) {
                hitPoints = 2;
            }
            for (int j = 0; j < 15; j++) {
                Point upperLeft = new Point(25 + 50 * j, 100 + 20 * i);
                blocksInLevel.add(new Block(upperLeft, 50, 20, blocksColors[i],
                        hitPoints));
            }
        }
        this.setBlocksInLevel(blocksInLevel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawBackgroundShapes(DrawSurface d) {
        Color[] colors = {new Color(211, 211, 211), new Color(190, 190, 190),
                new Color(170, 170, 170) };
        int leftX = this.firstCloudLeftX;

        // draw the rain for the first cloud
        for (int i = 0; i < 11; i++) {
            d.setColor(Color.WHITE);
            d.drawLine((leftX + 7 * i) % 800, 400, (leftX + 7 * i) % 800 - 40,
                    600);
            if (leftX + 7 * i >= 800) {
                d.drawLine(leftX + 7 * i, 400, leftX + 7 * i - 40, 600);
            }
        }

        // draw the first cloud
        Ball[] cloud1 = {new Ball(new Point(leftX, 390), 25, colors[0]),
                new Ball(new Point((leftX + 20) % 800, 410), 25, colors[0]),
                new Ball(new Point((leftX + 30) % 800, 380), 25, colors[1]),
                new Ball(new Point((leftX + 50) % 800, 410), 15, colors[2]),
                new Ball(new Point((leftX + 70) % 800, 390), 30, colors[2]) };
        for (int i = 0; i < cloud1.length; i++) {
            cloud1[i].drawOn(d);
            d.setColor(cloud1[i].getColor());
            d.drawCircle(cloud1[i].getX(), cloud1[i].getY(),
                    cloud1[i].getSize());
        }

        // draw the rain for the second cloud
        for (int i = 0; i < 11; i++) {
            d.setColor(Color.WHITE);
            d.drawLine((leftX + 500 + 7 * i) % 800, 520,
                    (leftX + 500 + 7 * i) % 800 - 40, 600);
            if (leftX + 500 + 7 * i >= 800) {
                d.drawLine(leftX + 500 + 7 * i, 520, leftX + 460 + 7 * i, 600);
            }
        }

        // draw the second cloud
        Ball[] cloud2 = {
                new Ball(new Point((leftX + 500) % 800, 510), 25, colors[0]),
                new Ball(new Point((leftX + 520) % 800, 530), 25, colors[0]),
                new Ball(new Point((leftX + 530) % 800, 500), 25, colors[1]),
                new Ball(new Point((leftX + 550) % 800, 530), 15, colors[2]),
                new Ball(new Point((leftX + 570) % 800, 500), 30, colors[2]) };
        for (int i = 0; i < cloud2.length; i++) {
            cloud2[i].drawOn(d);
            d.setColor(cloud2[i].getColor());
            d.drawCircle(cloud2[i].getX(), cloud2[i].getY(),
                    cloud2[i].getSize());
        }

        // draw the rain for the third cloud
        for (int i = 0; i < 11; i++) {
            d.setColor(Color.WHITE);
            d.drawLine((leftX + 300 + 7 * i) % 800, 380,
                    (leftX + 300 + 7 * i) % 800 - 130, 600);
            if (leftX + 300 + 7 * i >= 800) {
                d.drawLine(leftX + 300 + 7 * i, 380, leftX + 170 + 7 * i, 600);
            }
        }

        // draw the third cloud
        Ball[] cloud3 = {
                new Ball(new Point((leftX + 300) % 800, 370), 25, colors[0]),
                new Ball(new Point((leftX + 320) % 800, 390), 25, colors[0]),
                new Ball(new Point((leftX + 330) % 800, 360), 25, colors[1]),
                new Ball(new Point((leftX + 350) % 800, 390), 15, colors[2]),
                new Ball(new Point((leftX + 370) % 800, 360), 30, colors[2]) };
        for (int i = 0; i < cloud3.length; i++) {
            cloud3[i].drawOn(d);
            d.setColor(cloud3[i].getColor());
            d.drawCircle(cloud3[i].getX(), cloud3[i].getY(),
                    cloud3[i].getSize());
        }

        this.firstCloudLeftX++;
        if (this.firstCloudLeftX == 800) {
            this.firstCloudLeftX = 0;
        }
    }
}
