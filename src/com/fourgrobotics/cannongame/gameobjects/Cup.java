package com.fourgrobotics.cannongame.gameobjects;

import com.fourgrobotics.cannongame.util.SpriteLoader;

import javax.swing.*;
import java.awt.*;

/**
 * cup the ball will go in
 */
public class Cup {
    
    /**
     * left cuboid to make cup
     */
    private Physics leftSide;
    
    /**
     * right cuboid to make cup
     */
    private Physics rightSide;
    
    /**
     * bottom cuboid to make cup
     */
    private Physics bottom;
    
    /**
     * cup pos x
     */
    private double x;
    
    /**
     * cup pos y
     */
    private double y;
    
    /**
     * sprite used for the cup
     */
    private Image cupSprite;
    
    /**
     * create a new cup at position x, y
     *
     * @param x x pos
     * @param y y pos
     */
    public Cup(double x, double y) {
        leftSide = new Physics();
        rightSide = new Physics();
        bottom = new Physics();
        leftSide.setGravityMultiplier(0);
        rightSide.setGravityMultiplier(0);
        bottom.setGravityMultiplier(0);
        bottom.setObjectWidth(44);
        bottom.setObjectHeight(10);
        leftSide.setObjectWidth(10);
        rightSide.setObjectWidth(10);
        leftSide.setObjectHeight(24);
        rightSide.setObjectHeight(24);
        bottom.setPositionX(x);
        bottom.setPositionY(y);
        leftSide.setPositionX(x);
        leftSide.setPositionY(y - 24);
        rightSide.setPositionY(y - 24);
        rightSide.setPositionX(x + 34);
        this.x = x;
        this.y = y;
        cupSprite = SpriteLoader.CUP;
        tickCup();
    }
    
    /**
     * set x position of cup
     *
     * @param x x pos
     */
    public void setX(double x) {
        this.x = x;
        bottom.setPositionX(x);
        leftSide.setPositionX(x);
        rightSide.setPositionX(x + 34);
    }
    
    /**
     * set y position of cup
     *
     * @param y y pos
     */
    public void setY(double y) {
        this.y = y;
        bottom.setPositionY(y);
        leftSide.setPositionY(y - 24);
        rightSide.setPositionY(y - 24);
    }
    
    /**
     * check if a ball is entirely in a cup
     *
     * @param ball ball to check
     * @return true if ball is in cup
     */
    public boolean containsBall(Ball ball) {
        return x + 10 <= ball.getPositionX() &&
                x + 34 >= ball.getPositionX() + ball.getObjectWidth() &&
                y - 24 <= ball.getPositionY() &&
                y >= ball.getPositionY() + ball.getObjectHeight();
    }
    
    /**
     * tick the physics object that make up the cup
     */
    public void tickCup() {
        leftSide.tickPhysics();
        rightSide.tickPhysics();
        bottom.tickPhysics();
    }
    
    /**
     * draw the cup sprite
     *
     * @param panel game panel
     * @param g graphics
     */
    public void drawCup(JPanel panel, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(cupSprite, (int) x + 5, (int) y - 24, panel);
    }
    
    /**
     * move the cup to a new position
     *
     * @param cupX x pos
     * @param cupY y pos
     */
    public void move(int cupX, int cupY) {
        setX(cupX);
        setY(cupY);
        tickCup();
    }
}
