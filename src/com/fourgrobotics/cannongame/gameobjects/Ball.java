package com.fourgrobotics.cannongame.gameobjects;

import com.fourgrobotics.cannongame.util.SpriteLoader;

import javax.swing.*;
import java.awt.*;

/**
 * ball object used in the game, has physics
 */
public class Ball extends Physics {
    
    /**
     * ball sprite
     */
    private Image currentSprite;
    
    /**
     * construct a new ball with boundaries for its motion in the window
     */
    public Ball() {
        setCurrentSprite(SpriteLoader.BALL);
        setRightBound(1500);
        setBottomBound(575);
        setTopBound(-500);
    }
    
    /**
     * tick the ball physics
     */
    public void tickBall() {
        tickPhysics();
    }
    
    /**
     * draw the ball sprite where the ball is
     *
     * @param panel panel to draw on
     * @param g graphics
     */
    public void drawBall(JPanel panel, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if(getPositionX() == 0 && getPositionY() == 0) {
            setPositionX(36);
            setPositionY(532);
        }
        g2d.drawImage(currentSprite, (int) getPositionX()-2, (int) getPositionY()-2, panel);
    }
    
    /**
     * set the sprite of the ball
     *
     * @param sprite image
     */
    private void setCurrentSprite(Image sprite) {
        currentSprite = sprite;
        setObjectWidth(12);
        setObjectHeight(12);
    }
}
