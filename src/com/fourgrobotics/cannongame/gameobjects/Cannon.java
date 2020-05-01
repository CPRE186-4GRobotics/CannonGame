package com.fourgrobotics.cannongame.gameobjects;

import com.fourgrobotics.cannongame.util.KeyboardListener;
import com.fourgrobotics.cannongame.util.SpriteLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

/**
 * cannon to launch ball in game
 */
public class Cannon {
    
    /**
     * cannon main sprite
     */
    private Image cannonSprite;
    
    /**
     * cannon base sprite
     */
    private Image baseSprite;
    
    /**
     * angle cannon is launching at
     */
    private double angle = 45.0;
    
    /**
     * x location of top of cannon
     */
    private int cannonX = 30;
    
    /**
     * y location of top of cannon
     */
    private int cannonY = 525;
    
    /**
     * x location of base of cannon
     */
    private static int baseX = 30;
    
    /**
     * y location of base of cannon
     */
    private static int baseY = 531;
    
    /**
     * game panel
     */
    private static JPanel panel;
    
    /**
     * if the ball was launched
     */
    private boolean launched;
    
    /**
     * timer from game panel
     */
    private Timer timer;
    
    /**
     * ball to put in cannon
     */
    private Ball ball;
    
    /**
     * velocity to launch ball at
     */
    private double velocity = 10;
    
    /**
     * constructs the cannon for the game
     *
     * @param ball ball to load the cannon with
     * @param timer timer from game panel
     */
    public Cannon(Ball ball, Timer timer) {
        cannonSprite = SpriteLoader.CANNON;
        baseSprite = SpriteLoader.BASE;
        launched = false;
        this.timer = timer;
        this.ball = ball;
    }
    
    /**
     * tick the cannons physics and tick the move method
     */
    public void tickCannon() {
        move();
        if (!launched) {
            ball.setVelocityX(0);
            ball.setVelocityY(0);
            ball.setAccelerationX(0);
            ball.setAccelerationY(0);
            ball.setPositionX(36);
            ball.setPositionY(532);
        }
    }
    
    /**
     * draw the pieces of the cannon along with its rotation
     *
     * @param panel game panel
     * @param g graphics
     */
    public void drawCannon(JPanel panel, Graphics g) {
        this.panel = panel;
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(baseSprite, baseX, baseY, panel);
        AffineTransform aT = new AffineTransform();
        g2d.transform(aT.getRotateInstance(-Math.toRadians(angle), cannonX + 18, cannonY + 18));
        g2d.drawImage(cannonSprite, cannonX, cannonY, panel);
        g2d.transform(aT.getRotateInstance(Math.toRadians(angle), cannonX + 18, cannonY+18));
    }
    
    /**
     * handles the moving of the cannon and launching of the ball
     */
    private void move() {
        if (KeyboardListener.getPressed().contains(KeyEvent.VK_D)) {
            if (angle > 0) angle -= 0.1;
        }
        if (KeyboardListener.getPressed().contains(KeyEvent.VK_A)) {
            if (angle < 90) angle += 0.1;
        }
        if (KeyboardListener.getPressed().contains(KeyEvent.VK_S)) {
            if (velocity > 5) velocity -= 0.01;
        }
        if (KeyboardListener.getPressed().contains(KeyEvent.VK_W)) {
            if (velocity < 15) velocity += 0.01;
        }
        if (KeyboardListener.getPressed().contains(KeyEvent.VK_SPACE)) {
            if (!launched) {
                launched = true;
                ball.setVelocityX(velocity * Math.cos(Math.toRadians(angle)));
                ball.setVelocityY(velocity * Math.sin(Math.toRadians(angle)));
            }
        }
    }
    
    /**
     * get angle of cannon and that the ball will launch at
     *
     * @return angle of cannon
     */
    public double getAngle() {
        return angle;
    }
    
    /**
     * get how fast the ball will be launched at
     *
     * @return velocity for ball
     */
    public double getVelocity() {
        return velocity;
    }
    
    /**
     * set the angle of rotation for the cannon
     *
     * @param angle rotation in degrees
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }
    
    /**
     * set the velocity the ball will be launched at
     *
     * @param velocity velocity for ball
     */
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }
    
    /**
     * get whether the cannon has launched
     *
     * @return true if cannon has launched
     */
    public boolean isLaunched() {
        return launched;
    }
    
    /**
     * set whether the cannon has launched or not
     *
     * @param launched if the cannon has launched
     */
    public void setLaunched(boolean launched) {
        this.launched = launched;
    }
}
