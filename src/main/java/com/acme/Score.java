package com.acme;
import java.io.*;
import java.awt.*;


public class Score {
    private int score;
    private int highScore;
    private static final String HIGH_SCORE_FILE = "highscore.txt"; 
    
    public Score() {
        this.score = 0;
        this.highScore = loadHighScore();
    }

    
    public void increaseScore() {
        score++;
        if (score > highScore) {
            highScore = score; 
            saveHighScore();
        }
    }

    
    public void resetScore() {
        score = 0;
    }

    
    public int getCurrentScore() {
        return score;
    }

    public void displayScore(Graphics g, int tileSize) {
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.setColor(Color.white);
        g.drawString("Score: " + score, tileSize - 16, tileSize);
        g.drawString("High Score: " + highScore, tileSize - 16, tileSize * 2);
    }

     private void saveHighScore() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HIGH_SCORE_FILE))) {
            writer.write(Integer.toString(highScore)); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    private int loadHighScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(HIGH_SCORE_FILE))) {
            return Integer.parseInt(reader.readLine()); 
        } catch (IOException | NumberFormatException e) {
            
            return 0;
        }
    }

}
    
    