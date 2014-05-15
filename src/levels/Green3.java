package levels;

import arkanoid.Block;

import biuoop.DrawSurface;

import core.Velocity;

import geometry.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * The third level.
 * @author Tomer Davidor
 * @author Guy Blachar
 */
public class Green3 extends BaseLevel {
    private static final Random RAND = new Random();
    /**
     * Constructor.
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     */
    public Green3(int screenWidth, int screenHeight) {
        this.setLevelName("Green 3");
        this.setBackground(new Background(screenWidth, screenHeight,
                Color.GREEN.darker()
                .darker()));
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
        List<Velocity> ballsVelocities = new ArrayList<Velocity>();
        ballsVelocities.add(Velocity.fromAngleAndSpeed(45, 15));
        ballsVelocities.add(Velocity.fromAngleAndSpeed(-45, 15));
        this.setBallsVelocities(ballsVelocities);
        this.setNumberOfBalls(2);
    }

    /**
     * Create the blocks of the game.
     */
    private void createBlocks() {
        List<Block> blocksInLevel = new LinkedList<Block>();
        Color[] colors = {Color.WHITE, Color.BLUE, Color.YELLOW, Color.RED,
                Color.GRAY };
        for (int i = 0; i < colors.length; i++) {
            int hits = 1;
            // Top row
            if (i == colors.length - 1) {
                hits = 2;
            }
            for (int j = 0; j < i + 7; j++) {
                Point upperLeft = new Point(725 - 50 * j, 250 - 20 * i);
                Block block = new Block(upperLeft, 50, 20, colors[i], hits);
                blocksInLevel.add(block);
            }
        }
        this.setBlocksInLevel(blocksInLevel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void drawBackgroundShapes(DrawSurface d) {
        Point centerAntena = new Point(100, 200);
        int lengthAntena = 250;
        int widthAntena = 10;
        int widthBuilding = 70;
        int heightBuilding = 150;

        Point topBuilding = new Point(centerAntena.getX() - widthBuilding / 2,
                centerAntena.getY() + lengthAntena);
        Color[] antenaColors = {Color.ORANGE, Color.ORANGE.darker(),
                Color.WHITE };
        Color buildingColor = Color.DARK_GRAY;
        // Draw Base
        int widthBase = widthBuilding / 2;
        int heightBase = heightBuilding / 4;
        d.setColor(buildingColor.darker());
        d.fillRectangle((int) topBuilding.getX(), (int) topBuilding.getY(),
                widthBuilding, heightBuilding);
        // Draw Windows
        int windowRows = 5;
        int windowCols = 5;
        int vSpace = 5;
        int hSpace = 5;
        int windowWidth = 8;
        int windowHeight = 25;
        Color windowColor = Color.WHITE;
        int startX = (int) topBuilding.getX() + vSpace;
        int startY = (int) topBuilding.getY() + hSpace;
        d.setColor(windowColor);
        for (int row = 0; row < windowRows; row++) {
            for (int col = 0; col < windowCols; col++) {
                if (Green3.RAND.nextInt() % 13 == 0) {
                    d.setColor(windowColor.darker());
                } else {
                    d.setColor(windowColor);
                }
                // Draw Window
                d.fillRectangle(startX + col * (vSpace + windowWidth), startY
                        + row * (hSpace + windowHeight), windowWidth,
                        windowHeight);
            }
        }

        // Draw Pole
        d.setColor(buildingColor.brighter());
        d.fillRectangle((int) centerAntena.getX() - widthAntena / 2,
                (int) centerAntena.getY(), widthAntena, lengthAntena);

        // Draw Base
        d.setColor(buildingColor);
        d.fillRectangle((int) topBuilding.getX() + widthBase / 2,
                (int) topBuilding.getY() - heightBase, widthBase, heightBase);

        // Draw Lamp
        for (int i = 0; i < antenaColors.length; i++) {
            d.setColor(antenaColors[i]);
            d.fillCircle((int) centerAntena.getX(), (int) centerAntena.getY(),
                    10 - 4 * i + Green3.RAND.nextInt(2));
        }

    }

}
