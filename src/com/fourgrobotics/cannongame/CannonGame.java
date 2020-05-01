package com.fourgrobotics.cannongame;

import com.fourgrobotics.cannongame.panels.GamePanel;
import com.fourgrobotics.cannongame.panels.MenuPanel;
import com.fourgrobotics.cannongame.util.Score;
import com.fourgrobotics.cannongame.util.SpriteLoader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * CannonGame is a game like cup pong, but the person throwing it is replaced with a cannon. This is a game created as
 * part of a CPR E 186 project at Iowa State University.
 *
 * @author Jeremy Noesen, Gavin Canfield, Andrew Bowen, Zachary Hirst
 */
public class CannonGame extends JFrame {
    /**
     * scoreboard to keep scores
     */
    private Score s;
    
    /**
     * initializes the ui when making a new instance
     *
     * @throws IOException
     */
    public CannonGame() throws IOException {
        initializeUI();
    }
    
    /**
     * runs the game
     *
     * @param args
     */
    public static void main(String[] args) {
        new SpriteLoader();
        
        EventQueue.invokeLater(() -> {
            CannonGame game = null;
            try {
                game = new CannonGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
            game.setVisible(true);
        });
    }
    
    
    /**
     * create the window and add components to it to create the UI
     *
     * @throws IOException
     */
    private void initializeUI() throws IOException {
        s = new Score();
        
        GamePanel gamePanel = new GamePanel(s);
        add(gamePanel);
        setGlassPane(new MenuPanel(gamePanel, s));
        getGlassPane().setVisible(true);
        
        
        setSize(1000, 750);
        setResizable(false);
        setTitle("Cannon Pong");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
