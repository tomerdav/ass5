package arkanoid;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import core.Collidable;
import core.Sprite;
import core.Velocity;

import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * @author Tomer Davidor
 * @author Guy Blachar
 * @version 1.0
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle border;
    private Color color;
    private int speed;
    private int leftLimit;
    private int rightlimit;

    /**
     * Constructor.
     * @param keyboard a KeyboardSenser instance
     * @param rectangle to represent the paddle
     * @param color its color
     * @param speed the speed of the paddle
     */
    public Paddle(KeyboardSensor keyboard, Rectangle rectangle, Color color,
            int speed) {
        this.keyboard = keyboard;
        this.border = rectangle;
        this.color = color;
        this.speed = speed;
    }

    /**
     * Move the paddle to the left.
     */
    public void moveLeft() {
        Point newUpperLeft = this.border.getUpperLeft();
        // Move the upper left to the left
        newUpperLeft = new Point(
                this.border.getUpperLeft().getX() - this.speed, this.border
                        .getUpperLeft().getY());
        if (newUpperLeft.getX() >= this.leftLimit) {
            // Create new Rectangle
            this.border = new Rectangle(newUpperLeft, this.border.getWidth(),
                    this.border.getHeight());
        }
    }

    /**
     * Moves the paddle to the right.
     */
    public void moveRight() {
        Point newUpperLeft = this.border.getUpperLeft();
        // Move the upper left to the right
        newUpperLeft = new Point(
                this.border.getUpperLeft().getX() + this.speed, this.border
                        .getUpperLeft().getY());
        if (newUpperLeft.getX() + this.border.getWidth() <= this.rightlimit) {
            this.border = new Rectangle(newUpperLeft, this.border.getWidth(),
                    this.border.getHeight());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawOn(DrawSurface d) {
        Point upperLeft = this.border.getUpperLeft();
        // Inside
        d.setColor(this.color);
        d.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(),
                (int) this.border.getWidth(), (int) this.border.getHeight());
        // Border
        d.setColor(Color.BLACK);
        d.drawRectangle((int) upperLeft.getX(), (int) upperLeft.getY(),
                (int) this.border.getWidth(), (int) this.border.getHeight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.border;
    }

    /**
     * The speed of the paddle.
     * @return the speed of the paddle
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
            Velocity currentVelocity) {
        double retaliationAngle = currentVelocity.getAngle();
        double topLeftX = this.border.getUpperLeft().getX();
        if (collisionPoint.getY() == this.border.getUpperLeft().getY()
                && currentVelocity.getDy() >= 0) {
            // Fun paddle with 5 regions
            if (collisionPoint.getX() <= topLeftX
                    + this.border.getWidth() / 5) {
                retaliationAngle = 300;
            } else if (collisionPoint.getX() <= topLeftX + 2
                    * this.border.getWidth() / 5) {
                retaliationAngle = 330;
            } else if (collisionPoint.getX() <= topLeftX + 3
                    * this.border.getWidth() / 5) {
                retaliationAngle += 180;
                retaliationAngle *= -1;
            } else if (collisionPoint.getX() <= topLeftX + 4
                    * this.border.getWidth() / 5) {
                retaliationAngle = 30;
            } else {
                retaliationAngle = 60;
            }
        } else if (collisionPoint.getX() == topLeftX
                || collisionPoint.getX() == this.border.getLowerRight().getX()
                && collisionPoint.getX() >= this.leftLimit + this.speed
                && collisionPoint.getX() <= this.rightlimit - this.speed) {
            retaliationAngle = 360 - retaliationAngle;
        } else {
            return currentVelocity;
        }
        return Velocity.fromAngleAndSpeed(retaliationAngle,
                currentVelocity.getSpeed());
    }

    /**
     * Add the limits to the paddle in which he can move.
     * @param left the left limit of the movement
     * @param right the right limit of the movement
     */
    public void setLimits(int left, int right) {
        this.leftLimit = left;
        this.rightlimit = right;
    }

    /**
     * Move the paddle to a specific place.
     * @param upperLeft the upper-left point of the new position
     */
    public void movePaddleTo(Point upperLeft) {
        this.border = new Rectangle(upperLeft, this.border.getWidth(),
                this.border.getHeight());
    }

    /**
     * Add this paddle to the game.
     * @param g the game to add to
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
