package com.acme;



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
            System.out.println(score);
        }
    }

    
    public void resetScore() {
        score = 0;
    }

    
    public int getCurrentScore() {
        return score;
    }


}
    
    