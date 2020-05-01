package com.fourgrobotics.cannongame.panels;

import com.fourgrobotics.cannongame.gameobjects.Ball;
import com.fourgrobotics.cannongame.gameobjects.Cannon;
import com.fourgrobotics.cannongame.gameobjects.Cup;
import com.fourgrobotics.cannongame.util.KeyboardListener;
import com.fourgrobotics.cannongame.util.Score;
import com.fourgrobotics.cannongame.util.SpriteLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

/**
 * main game for the application. this shows all components that are meant to be played.
 */
public class GamePanel extends JPanel implements ActionListener {
    
    /**
     * ball for game
     */
    private Ball ball;
    
    /**
     * cannon for game
     */
    private Cannon cannon;
    
    /**
     * cup for game
     */
    private Cup cup;
    
    /**
     * timer to loop with delay
     */
    private Timer timer;
    
    /**
     * score instance
     */
    private Score s;
    
    /**
     * cup pos x
     */
    private int cupX = 850;
    
    /**
     * cup pos y
     */
    private int cupY = 200;
    
    /**
     * random to randomize positions for cup
     */
    private Random r;
    
    /**
     * score text in game
     */
    JLabel scoreText;
    
    /**
     * velocity text in game
     */
    JLabel velText;
    
    /**
     * angle text in game
     */
    JLabel angleText;
    
    /**
     * create the game panel, which is the game itself. all components are added here, such as status texts, the cup,
     * ball, and cannon
     *
     * @param s scoreboard instance
     */
    public GamePanel(Score s) {
        setOpaque(false);
        ball = new Ball();
        timer = new Timer(5, this);
        cannon = new Cannon(ball, timer);
        cup = new Cup(cupX, cupY);
        this.s = s;
        r = new Random(400);
        scoreText = new JLabel();
        velText = new JLabel();
        angleText = new JLabel();
        setLayout(null);
        scoreText.setText("Score: " + s.getScore() + " points");
        velText.setText(String.format("Velocity: %.1f units", cannon.getVelocity()));
        angleText.setText(String.format("Angle: %.1fº", cannon.getAngle()));
        scoreText.setFont(new Font("Futura", 1, 30));
        velText.setFont(new Font("Futura", 1, 30));
        angleText.setFont(new Font("Futura", 1, 30));
        Color color = new Color(255, 175, 0);
        scoreText.setForeground(color);
        velText.setForeground(color);
        angleText.setForeground(color);
        add(scoreText);
        add(velText);
        add(angleText);
        scoreText.setBounds(10, 10, 300, 50);
        velText.setBounds(10, 50, 400, 50);
        angleText.setBounds(10, 90, 300, 50);
        addKeyListener(new KeyboardListener());
    }
    
    /**
     * ticks through game frames and checks for win or lose events and updates motions
     *
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        cannon.tickCannon();
        ball.tickBall();
        requestFocus();
        if (cup.containsBall(ball) && cannon.isLaunched()) {
            s.goal();
            try {
                s.setHighScoreInFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            cupY = r.nextInt(525);
            cupX = r.nextInt(700) + 250;
            cup.move(cupX, cupY);
            cannon.setLaunched(false);
        } else if (ball.isOnGround()) {
            try {
                s.setHighScoreInFile();
                s.setScore(0);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            cannon.setLaunched(false);
            cannon.setAngle(45);
            cannon.setVelocity(10);
        }
        scoreText.setText("Score: " + s.getScore() + " points");
        velText.setText(String.format("Velocity: %.1f units", cannon.getVelocity()));
        angleText.setText(String.format("Angle: %.1fº", cannon.getAngle()));
    }
    
    /**
     * paint all graphics for the game
     *
     * @param g graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        repaint(0, 0, 1000, 750);
        ((Graphics2D) g).drawImage(SpriteLoader.BACKGROUND, 0, 0, this);
        ball.drawBall(this, g);
        cannon.drawCannon(this, g);
        cup.drawCup(this, g);
        Toolkit.getDefaultToolkit().sync();
    }
    
    /**
     * start the timer
     */
    public void start() {
        timer.start();
    }
}
