package com.fourgrobotics.cannongame.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * loader for all images in the game
 */
public class SpriteLoader {
    
    /**
     * background image for the game
     */
    public static Image BACKGROUND;
    
    /**
     * ball texture
     */
    public static Image BALL;
    
    /**
     * base texture for cannon
     */
    public static Image BASE;
    
    /**
     * cannon texture
     */
    public static Image CANNON;
    
    /**
     * game logo for menu
     */
    public static Image LOGO;
    
    /**
     * start button for menu
     */
    public static Image START_BUTTON;
    
    /**
     * cup texture used for catching the ball
     */
    public static Image CUP;
    
    /**
     * loads all sprites
     */
    public SpriteLoader() {
        try {
            BALL = ImageIO.read(getClass().getResourceAsStream("/ball.png"));
            BACKGROUND = ImageIO.read(getClass().getResourceAsStream("/background.png"));
            BASE = ImageIO.read(getClass().getResourceAsStream("/base.png"));
            CANNON = ImageIO.read(getClass().getResourceAsStream("/cannon.png"));
            LOGO = ImageIO.read(getClass().getResourceAsStream("/logo.png"));
            START_BUTTON = ImageIO.read(getClass().getResourceAsStream("/button.png"));
            CUP = ImageIO.read(getClass().getResourceAsStream("/cup.png"));
        } catch (IOException e) {
            System.out.println("Failed to load sprites!");
            e.printStackTrace();
        }
    }
}
