package com.acme;
import java.io.*;
import java.awt.*;


public class Score {
    private int score;
    private int highScore;

    
    public Score() {
        this.score = 0;
        
    }

    
    public void increaseScore() {
        score++;
        if (score > highScore) {
            highScore = score; 
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

}
    
    