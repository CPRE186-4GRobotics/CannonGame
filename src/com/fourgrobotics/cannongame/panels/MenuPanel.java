package com.fourgrobotics.cannongame.panels;

import com.fourgrobotics.cannongame.util.Score;
import com.fourgrobotics.cannongame.util.SpriteLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * meu panel for info and starting the game
 */
public class MenuPanel extends JPanel implements ActionListener {
    
    /**
     * scoreboard instance
     */
    private Score s;
    
    /**
     * game panel instance
     */
    private GamePanel g;
    
    /**
     * creates a menu panel that will start the game and also show high scores
     *
     * @param game game panel
     * @param s score instance
     * @throws IOException
     */
    public MenuPanel(GamePanel game, Score s) throws IOException {
        g = game;
        g.scoreText.setVisible(false);

        setOpaque(false);
        setLayout(new GridBagLayout());
    
        JButton start = new JButton();
        start.setIcon(new ImageIcon(SpriteLoader.START_BUTTON));
        start.setBorder(null);
        start.setBorderPainted(false);
        start.addActionListener(this::actionPerformed);

        JLabel highscore = new JLabel("High Score: " + s.getHighScore());
        highscore.setForeground(new Color(255, 175, 0));
        highscore.setFont(new Font("Futura",1,40));
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(355, 2, 2, 2);
        c.ipady = 5;
        c.gridy = 1;
        add(start, c);

        c.insets = new Insets(20, 2, 150, 2);
        c.gridy = 2;
        add(highscore, c);
        
        JLabel info = new JLabel("Change Velocity: [W, S], Change Angle: [A, D], Fire: [SPACE]");
        info.setForeground(new Color(255, 220, 200));
        info.setFont(new Font("Futura",1,20));
        c.insets = new Insets(2, 2, 2, 2);
        c.gridy = 3;
        add(info, c);
    }
    
    /**
     * this will start the game itself from the menu
     *
     * @param e action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        g.start();
        g.scoreText.setVisible(true);
    }
    
    /**
     * draws logo
     *
     * @param g graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        ((Graphics2D) g).drawImage(SpriteLoader.LOGO, 0, 0, this);
    }
}
