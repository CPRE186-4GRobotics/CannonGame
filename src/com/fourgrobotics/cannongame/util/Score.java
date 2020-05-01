package com.fourgrobotics.cannongame.util;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

/**
 * A class that handles the score system of the game.
 */
public class Score {
    /**
     * total points for the instance
     */
    private int totalPoints;
    
    /**
     * high score for all games
     */
    private int highScore;
    
    
    /**
     * create a new score instance
     *
     * @throws IOException
     */
    public Score() throws IOException {
        totalPoints = 0;
        readScore();
    }
    
    /**
     * get the current score for the game
     *
     * @return current score
     */
    public int getScore() {
        return totalPoints;
    }
    
    /**
     * set current score
     *
     * @param totalPoints points
     */
    public void setScore(int totalPoints) {
        this.totalPoints = totalPoints;
    }
    
    /**
     * get the high score saved in the file
     *
     * @return games high score
     */
    public int getHighScore() {
        return highScore;
    }
    
    /**
     * adds points to totalPoints
     */
    public void goal() {
        totalPoints += 1;
    }
    
    /**
     * reads the high score from file
     *
     * @throws IOException
     */
    private void readScore() throws IOException {
        File file = new File("score.txt");
        if (!file.exists()) {
            resetScores();
        }
        Scanner sc = new Scanner(file);
        highScore = sc.nextInt();
        sc.close();
    }
    
    /**
     * updates the file containing the high score
     *
     * @throws IOException
     */
    public void setHighScoreInFile() throws IOException {
        File outFile = new File("score.txt");
        if (!outFile.exists()) {
            outFile.createNewFile();
        }
        PrintWriter out = new PrintWriter(outFile);
        if (totalPoints > highScore) out.println(totalPoints);
        else out.println(highScore);
        out.close();
    }
    
    /**
     * Resets the score back to 0
     *
     * @throws IOException
     */
    private static void resetScores() throws IOException {
        File outFile = new File("score.txt");
        if (!outFile.exists()) {
            outFile.createNewFile();
        }
        PrintWriter out = new PrintWriter(outFile);
        out.println(0);
        out.close();
    }
    
}